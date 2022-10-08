package com.office.qljt.qljtoffice.strategy.impl;

import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.service.UserService;
import com.office.qljt.qljtoffice.strategy.SocialLoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 第三方登录抽象模板
 *
 * @author lib
 * @date 2021/07/28
 */
@Service
public abstract class AbstractSocialLoginStrategyImpl implements SocialLoginStrategy {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Resource
    private HttpServletRequest request;

    @Override
    public UserDTO login(String data) {
        // 创建登录信息
//        UserDTO userDTO;
//        // 获取用户ip信息
//        String ipAddress = IpUtils.getIpAddress(request);
//        String ipSource = IpUtils.getIpSource(ipAddress);
//        // 判断是否已注册
//
//        // 判断账号是否禁用
//        if (userDetailDTO.getIsDisable().equals(TRUE)) {
//            throw new BizException("账号已被禁用");
//        }
//        // 将登录信息放入springSecurity管理
//        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetailDTO, null, userDetailDTO.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(auth);
//        // 返回用户信息
//        return BeanCopyUtils.copyObject(userDetailDTO, UserInfoDTO.class);
        return null;
    }

//    /**
//     * 获取第三方token信息
//     *
//     * @param data 数据
//     * @return {@link SocialTokenDTO} 第三方token信息
//     */
//    public abstract SocialTokenDTO getSocialToken(String data);
//
//    /**
//     * 获取第三方用户信息
//     *
//     * @param socialTokenDTO 第三方token信息
//     * @return {@link SocialUserInfoDTO} 第三方用户信息
//     */
//    public abstract SocialUserInfoDTO getSocialUserInfo(SocialTokenDTO socialTokenDTO);
//
//    /**
//     * 获取用户账号
//     *
//     * @return {@link UserAuth} 用户账号
//     */
//    private UserAuth getUserAuth(SocialTokenDTO socialTokenDTO) {
//        return userAuthDao.selectOne(new LambdaQueryWrapper<UserAuth>()
//                .eq(UserAuth::getUsername, socialTokenDTO.getOpenId())
//                .eq(UserAuth::getLoginType, socialTokenDTO.getLoginType()));
//    }
//
//    /**
//     * 获取用户信息
//     *
//     * @param user      用户账号
//     * @param ipAddress ip地址
//     * @param ipSource  ip源
//     * @return {@link UserDetailDTO} 用户信息
//     */
//    private UserDetailDTO getUserDetail(UserAuth user, String ipAddress, String ipSource) {
//        // 更新登录信息
//        userAuthDao.update(new UserAuth(), new LambdaUpdateWrapper<UserAuth>()
//                .set(UserAuth::getLastLoginTime, LocalDateTime.now())
//                .set(UserAuth::getIpAddress, ipAddress)
//                .set(UserAuth::getIpSource, ipSource)
//                .eq(UserAuth::getId, user.getId()));
//        // 封装信息
//        return userDetailsService.convertUserDetail(user, request);
//    }
//
//    /**
//     * 新增用户信息
//     *
//     * @param socialToken token信息
//     * @param ipAddress   ip地址
//     * @param ipSource    ip源
//     * @return {@link UserDetailDTO} 用户信息
//     */
//    private UserDetailDTO saveUserDetail(SocialTokenDTO socialToken, String ipAddress, String ipSource) {
//        // 获取第三方用户信息
//        SocialUserInfoDTO socialUserInfo = getSocialUserInfo(socialToken);
//        // 保存用户信息
//        UserInfo userInfo = UserInfo.builder()
//                .nickname(socialUserInfo.getNickname())
//                .avatar(socialUserInfo.getAvatar())
//                .build();
//        userInfoDao.insert(userInfo);
//        // 保存账号信息
//        UserAuth userAuth = UserAuth.builder()
//                .userInfoId(userInfo.getId())
//                .username(socialToken.getOpenId())
//                .password(socialToken.getAccessToken())
//                .loginType(socialToken.getLoginType())
//                .lastLoginTime(LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())))
//                .ipAddress(ipAddress)
//                .ipSource(ipSource)
//                .build();
//        userAuthDao.insert(userAuth);
//        // 绑定角色
//        UserRole userRole = UserRole.builder()
//                .userId(userInfo.getId())
//                .roleId(RoleEnum.USER.getRoleId())
//                .build();
//        userRoleDao.insert(userRole);
//        return userDetailsService.convertUserDetail(userAuth, request);
//    }

}
