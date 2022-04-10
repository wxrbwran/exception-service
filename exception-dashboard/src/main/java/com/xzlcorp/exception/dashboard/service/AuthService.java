package com.xzlcorp.exception.dashboard.service;

import com.xzlcorp.exception.common.exception.XzlCorpException;
import com.xzlcorp.exception.common.exception.XzlCorpExceptionEnum;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.LoginRequest;
import com.xzlcorp.exception.dashboard.model.request.SignupRequest;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import com.xzlcorp.exception.common.utils.Md5Utils;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoran
 */
@Service
public class AuthService {

//  @Autowired
//  private UserMapper userMapper;

  public UserVO signup(SignupRequest request) throws NoSuchAlgorithmException {

//    SelectStatementProvider provider = SqlBuilder.select(UserMapper.selectList)
//        .from(UserDynamicSqlSupport.user)
//        .where(UserDynamicSqlSupport.email, isEqualTo(request.getEmail()))
//        .build()
//        .render(RenderingStrategies.MYBATIS3);
//    User oldUser = userMapper.selectOne(provider);
//    if (oldUser != null) {
//      throw new XzlCorpException(XzlCorpExceptionEnum.EMAIL_EXISTED);
//    } else {
//      String md5Password = Md5Utils.getMD5String(request.getPassword());
//      User user = new User();
//      BeanUtils.copyProperties(request, user);
//      user.setPassword(md5Password);
//      user.setActivated(true);
//      userMapper.insertSelective(user);
//      UserVO userVO = new UserVO();
//      BeanUtils.copyProperties(user, userVO);
//      return userVO;
//    }
    return null;
  }

  public UserVO login(LoginRequest request) throws NoSuchAlgorithmException {
//    SelectStatementProvider provider = SqlBuilder.select(UserMapper.selectList)
//        .from(UserDynamicSqlSupport.user)
//        .where(UserDynamicSqlSupport.email, isEqualTo(request.getEmail()))
//        .and(UserDynamicSqlSupport.password, isEqualTo(Md5Utils.getMD5String(request.getPassword())))
//        .build()
//        .render(RenderingStrategies.MYBATIS3);
//    User oldUser = userMapper.selectOne(provider);
//    if (oldUser == null) {
//      throw new XzlCorpException(XzlCorpExceptionEnum.INVALID_EMAIL_PASSWORD);
//    }
//    UserVO userVO = new UserVO();
//    BeanUtils.copyProperties(oldUser, userVO);
//    return userVO;
    return null;
  }
}
