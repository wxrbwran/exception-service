package com.xzlcorp.exception.dashboard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.LoginRequest;
import com.xzlcorp.exception.dashboard.model.request.SignupRequest;
import com.xzlcorp.exception.dashboard.model.request.UpdateUserRequest;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
* @author wuxiaoran
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-04-13 11:34:48
*/
public interface UserService extends IService<User> {

  UserVO signup(SignupRequest request) throws NoSuchAlgorithmException;

  UserVO login(LoginRequest request) throws NoSuchAlgorithmException;

  User getUserInfoById(Integer id);

  List<User> getUserInfoByIds(Integer[] userIds);

  UserVO getUserInfoByIdSimple(Integer id);

  UserVO handleUser2VO(User user);

  List<UserVO> handleUsers2VOList(List<User> users);

  User updateUser(User user);

  User updateUserById(Integer userId, UpdateUserRequest request);

  void bindUserWithProjectId(Integer projectId, Integer userId);

  void bindUserWithOrganizationId(Integer id, Integer admin);

  UserVO getFullUserInfo(Integer userId);
}
