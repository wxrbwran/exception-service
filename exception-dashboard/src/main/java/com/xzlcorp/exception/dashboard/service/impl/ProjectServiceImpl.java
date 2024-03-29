package com.xzlcorp.exception.dashboard.service.impl;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.common.utils.RedisConstants;
import com.xzlcorp.exception.common.utils.UniqueList;
import com.xzlcorp.exception.dashboard.mapper.ProjectMapper;
import com.xzlcorp.exception.dashboard.model.pojo.NotificationSetting;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingBrowser;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingEmail;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingEmails;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingWebHooks;
import com.xzlcorp.exception.dashboard.model.request.EditProjectRequest;
import com.xzlcorp.exception.dashboard.model.request.NotificationSettingRequest;
import com.xzlcorp.exception.dashboard.model.request.ProjectRequest;
import com.xzlcorp.exception.dashboard.model.vo.ProjectVO;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import com.xzlcorp.exception.dashboard.service.NotificationSettingService;
import com.xzlcorp.exception.dashboard.service.OrganizationService;
import com.xzlcorp.exception.dashboard.service.ProjectService;
import com.xzlcorp.exception.dashboard.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author wuxiaoran
* @description 针对表【t_project(项目表)】的数据库操作Service实现
* @createDate 2022-04-13 11:39:29
*/
@Service
@Slf4j
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project>
    implements ProjectService{

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private UserService userService;

  @Autowired
  private NotificationSettingService notificationSettingService;

  @Autowired
  private OrganizationService organizationService;

  @Override
  public List<Project> getAllProjectsByOrganizationId(Integer organizationId, Integer userId) {
    LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Project::getOrganization, organizationId);
    return this.list(queryWrapper);
  }
  @Override
  public List<ProjectVO> handleUsersInProjects(List<Project> projects) {
    List<ProjectVO> projectVOList = new ArrayList<>();
    Map<Integer, UserVO> userVOCache = new HashMap<>();
    projects.forEach(project -> {
      ProjectVO projectVO = new ProjectVO();
      BeanUtils.copyProperties(project, projectVO);
      List<Integer> users = new ArrayList<>();
      users.addAll(project.getUsers() != null ? Arrays.asList(project.getUsers()) : new ArrayList<>());
      List<UserVO> userVOList = new ArrayList<>();
      users.forEach(userId -> {
        if (userVOCache.containsKey(userId)) {
          log.info("cached!");
          userVOList.add(userVOCache.get(userId));
        } else {
          User user = userService.getUserInfoById(userId);
          UserVO userVO = new UserVO();
          BeanUtils.copyProperties(user, userVO);
          userVOCache.put(userId, userVO);
          userVOList.add(userVO);
        }
      });
      projectVO.setUsers(userVOList);
      projectVOList.add(projectVO);
    });

    return projectVOList;
  }

  private String createApiKey(ProjectRequest request) {
    String secret = Constant.APP_SECRET;
    byte[] key = secret.getBytes();
    HMac mac = new HMac(HmacAlgorithm.HmacSHA256, key);
    String data = request.getName() + "-" + request.getType() + "-"
        + request.getAdmin() + "-" + request.getOrganization();
    String macHex = mac.digestHex(data);
    return macHex;
  }

  private Project createProjectObject(ProjectRequest request) {
    Integer adminId = request.getAdmin();
    Integer organizationId = request.getOrganization();
    User admin = userService.getUserInfoById(adminId);
    String apiKey = createApiKey(request);
    var notificationSettingRequest = new NotificationSettingRequest();

    var webhooks = new NotificationSettingWebHooks();
    webhooks.setNotificationSettingWebHooks(new ArrayList<>());
    notificationSettingRequest.setWebhooks(webhooks);

    var browser = new NotificationSettingBrowser();
    browser.setOpen(false);
    browser.setData(null);
    notificationSettingRequest.setBrowser(browser);

    var emails = new NotificationSettingEmails();
    var email = new NotificationSettingEmail();
    if (admin.getEmail() != null) {
      email.setEmail(admin.getEmail());
      email.setOpen(true);
    }
    emails.getEmails().add(email);
    notificationSettingRequest.setEmails(emails);

    NotificationSetting setting = notificationSettingService.createNotificationSetting(notificationSettingRequest);
    Project project = new Project();
    project.setApiKey(apiKey);
    project.setName(request.getName());
    project.setType(request.getType());
    project.setAdmin(adminId);
    Integer[] users = {adminId};
    project.setUsers(users);
    project.setOrganization(organizationId);
    project.setNotificationSetting(setting.getId());
    this.save(project);
//    projectMapper.insertSelective(project);
    userService.bindUserWithProjectId(project.getId(), adminId);
    bindOrganizationWithProjectId(project.getId(), organizationId);
//    删除redis缓存
    userService.deleteRedisUserInfo(adminId);
    return project;
  };
  @Override
  public void bindOrganizationWithProjectId(Integer projectId, Integer organizationId) {
    Organization organization = organizationService.getById(organizationId);
    Integer[] projects = organization.getProjects();
    List<Integer> projectList = new ArrayList<>();
    if (projects != null) {
      projectList.addAll(Arrays.asList(projects));
    }
    if (!projectList.contains(projectId)) {
      projectList.add(projectId);
    }
    Integer[] newProjects = projectList.toArray(new Integer[projectList.size()]);
    organization.setProjects(newProjects);
    organizationService.update(organization);
  }
  @Override
  public ProjectVO saveToVO(ProjectRequest request) {
    Project project = createProjectObject(request);
    return handleProject2VO(project);
  }
  @Override
  public ProjectVO updateProject(Integer projectId, EditProjectRequest request) {
    Project project = new Project();
    BeanUtils.copyProperties(request, project);
    project.setId(projectId);
    this.updateById(project);
//    userService.deleteRedisUserInfo(adminId);
    return handleProject2VO(project);
  }
  @Override
  public ProjectVO handleProject2VO(Project project) {
    ProjectVO projectVO = new ProjectVO();
    BeanUtils.copyProperties(project, projectVO);
    return projectVO;
  }
  @Override
  public List<ProjectVO> handleProjects2VOList(List<Project> projects) {
    List<ProjectVO> projectVOList = new ArrayList<>();
    projects.forEach(project -> {
      ProjectVO projectVO = new ProjectVO();
      BeanUtils.copyProperties(project, projectVO);
      projectVOList.add(projectVO);
    });
    return projectVOList;
  }
  @Override
  public List<Project> getProjects(List<Integer> projectIds) {
    List<Project> projectList = this.listByIds(projectIds);
    return projectList;
  }

  @Override
  public Project getProjectByApiKey(String apiKey) {
    LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Project::getApiKey, apiKey);
    return this.getOne(queryWrapper);
  }

  @Override
  public void addUser(Integer projectId, List<Integer> userIds) {
    Project project = this.getById(projectId);
    List<Integer> users = Arrays.stream(project.getUsers()).collect(Collectors.toList());
    List<Integer> userIdSet = UniqueList.toUnique(users, userIds);
    project.setUsers(userIdSet.toArray(new Integer[userIdSet.size()]));
    this.updateById(project);
  }
}




