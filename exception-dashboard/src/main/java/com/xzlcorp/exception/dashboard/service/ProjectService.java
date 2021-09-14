package com.xzlcorp.exception.dashboard.service;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.dashboard.model.dao.ProjectDynamicSqlSupport;
import com.xzlcorp.exception.dashboard.model.dao.ProjectMapper;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSetting;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingBrowser;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingEmail;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingEmails;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingWebHooks;
import com.xzlcorp.exception.dashboard.model.request.NotificationSettingRequest;
import com.xzlcorp.exception.dashboard.model.request.ProjectRequest;
import com.xzlcorp.exception.dashboard.model.vo.ProjectVO;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoran
 */
@Service
@Slf4j
public class ProjectService {

  @Autowired
  private ProjectMapper projectMapper;

  @Autowired
  private UserService userService;

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private OrganizationService organizationService;

  public List<Project> getAllProjectsByOrganizationId(Integer organizationId, Integer userId) {
    SelectStatementProvider provider = SqlBuilder.select(ProjectMapper.selectList)
        .from(ProjectDynamicSqlSupport.project)
        .where(ProjectDynamicSqlSupport.organization, isEqualTo(organizationId))
        .build()
        .render(RenderingStrategies.MYBATIS3);
    
    List<Project> projects = projectMapper.selectMany(provider);

    return projects;
  }

  public List<ProjectVO> handleUsersInProjects(List<Project> projects) {
    List<ProjectVO> projectVOList = new ArrayList<>();
    Map<Integer, UserVO> userVOCache = new HashMap<>();
    projects.forEach(project -> {
      ProjectVO projectVO = new ProjectVO();
      BeanUtils.copyProperties(project, projectVO);
      List<Integer> users = new ArrayList<>();
      users.addAll(Arrays.asList(project.getUsers()));
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
    NotificationSettingRequest notificationSettingRequest = new NotificationSettingRequest();

    NotificationSettingWebHooks webhooks = new NotificationSettingWebHooks();
    webhooks.setNotificationSettingWebHooks(new ArrayList<>());
    notificationSettingRequest.setWebhooks(webhooks);

    NotificationSettingBrowser browser = new NotificationSettingBrowser();
    browser.setOpen(false);
    browser.setData(null);
    notificationSettingRequest.setBrowser(browser);

    NotificationSettingEmails emails = new NotificationSettingEmails();
    NotificationSettingEmail email = new NotificationSettingEmail();
    if (admin.getEmail() != null) {
      email.setEmail(admin.getEmail());
      email.setOpen(true);
    }
    emails.getEmails().add(email);
    notificationSettingRequest.setEmails(emails);

    NotificationSetting setting = notificationService.createNotificationSetting(notificationSettingRequest);
    Project project = new Project();
    project.setApiKey(apiKey);
    project.setName(request.getName());
    project.setType(request.getType());
    project.setAdmin(adminId);
    Integer[] users = {adminId};
    project.setUsers(users);
    project.setOrganization(organizationId);
    project.setNotificationSetting(setting.getId());

    projectMapper.insertSelective(project);
    userService.bindUserWithProjectId(project.getId(), adminId);
    bindOrganizationWithProjectId(project.getId(), organizationId);
    return project;
  };

  public void bindOrganizationWithProjectId(Integer projectId, Integer organizationId) {
    Organization organization = organizationService.getOrganizationById(organizationId);
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

  public ProjectVO save(ProjectRequest request) {
    Project project = createProjectObject(request);
    ProjectVO projectVO = new ProjectVO();
    BeanUtils.copyProperties(project, projectVO);
    return projectVO;
  }

  public List<ProjectVO> getProjects(List<Integer> projectIds) {
    List<ProjectVO> projectVOList = new ArrayList<>();
    projectIds.forEach(projectId -> {
      Project project = projectMapper.selectByPrimaryKey(projectId);
      ProjectVO projectVO = new ProjectVO();
      BeanUtils.copyProperties(project, projectVO);
      projectVOList.add(projectVO);
    });
    return projectVOList;
  }

  public Project getProjectById(Integer projectId) {
    Project project = projectMapper.selectByPrimaryKey(projectId);
    return project;
  }

  public Project getProjectByApiKey(String apiKey) {
    return projectMapper.selectOne(c ->
        c.where(ProjectDynamicSqlSupport.apiKey, isEqualTo(apiKey))
    );
  }
}
