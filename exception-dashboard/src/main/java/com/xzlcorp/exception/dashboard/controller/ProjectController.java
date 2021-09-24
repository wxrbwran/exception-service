package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.request.EditProjectRequest;
import com.xzlcorp.exception.dashboard.model.request.OrganizationRequest;
import com.xzlcorp.exception.dashboard.model.request.ProjectRequest;
import com.xzlcorp.exception.dashboard.model.vo.OrganizationVO;
import com.xzlcorp.exception.dashboard.model.vo.ProjectVO;
import com.xzlcorp.exception.dashboard.service.OrganizationService;
import com.xzlcorp.exception.dashboard.service.ProjectService;
import io.swagger.annotations.Api;
import java.util.List;
import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoran
 */

@RestController
//@RequestMapping({"/projects", "dashboard/projects"})
@RequestMapping({"/projects"})
@Api(value = "Projects", tags = {"项目相关接口"})
@Slf4j
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @PostMapping()
  @ApiOperation(value="创建项目", notes = "创建项目", httpMethod = "POST")
  public ApiRestResponse create(@Valid @RequestBody ProjectRequest request) {
    ProjectVO projectVO = projectService.save(request);
    return ApiRestResponse.success(projectVO);
  }

  @PatchMapping("{projectId}")
  @ApiOperation(value="更新项目", notes = "更新项目", httpMethod = "PATCH")
  public ApiRestResponse updateProjectById(
      @PathVariable Integer projectId,
      @Valid @RequestBody EditProjectRequest request) {
    ProjectVO projectVO = projectService.update(projectId, request);
    return ApiRestResponse.success(projectVO);
  }

  @GetMapping()
  public ApiRestResponse getAll(
      @RequestParam("organizationId") Integer organizationId,
      @RequestParam("userId") Integer userId
    ) {
    List<Project> projects = projectService.getAllProjectsByOrganizationId(organizationId, userId);
    List<ProjectVO> projectVOList =  projectService.handleUsersInProjects(projects);
    return  ApiRestResponse.success(projectVOList);
  }

  @GetMapping("getApiKeyByProjectId")
  public String getApiKeyByProjectId(Integer projectId){
    log.info("projects getApiKeyByProjectId projectId: {}", projectId);
    Project project = projectService.getProjectById(projectId);
    return project.getApiKey();
  }
}
