package com.xzlcorp.exception.dashboard.service;

import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzlcorp.exception.dashboard.model.request.EditProjectRequest;
import com.xzlcorp.exception.dashboard.model.request.ProjectRequest;
import com.xzlcorp.exception.dashboard.model.vo.ProjectVO;

import java.util.List;

/**
* @author wuxiaoran
* @description 针对表【t_project(项目表)】的数据库操作Service
* @createDate 2022-04-13 11:39:29
*/
public interface ProjectService extends IService<Project> {

  List<Project> getAllProjectsByOrganizationId(Integer organizationId, Integer userId);

  List<ProjectVO> handleUsersInProjects(List<Project> projects);

  void bindOrganizationWithProjectId(Integer projectId, Integer organizationId);

  ProjectVO saveToVO(ProjectRequest request);

//  ProjectVO update(Integer projectId, EditProjectRequest request);

  ProjectVO updateProject(Integer projectId, EditProjectRequest request);

  ProjectVO handleProject2VO(Project project);

  List<ProjectVO> handleProjects2VOList(List<Project> projects);

  List<Project> getProjects(List<Integer> projectIds);

  Project getProjectByApiKey(String apiKey);

  void addUser(Integer projectId, List<Integer> userIds);
}
