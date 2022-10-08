package com.office.qljt.qljtoffice.handler;

import com.alibaba.fastjson.JSON;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.UserUtils;
import com.office.qljt.qljtoffice.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.office.qljt.qljtoffice.constant.CommonConst.APPLICATION_JSON;


/**
 * 登录成功处理
 *
 * @author lib
 * @date 2021/07/28
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Autowired
    private UserDao userDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // 返回登录信息
        UserDTO userDTO = BeanCopyUtils.copyObject(UserUtils.getLoginUser(), UserDTO.class);
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.ok(userDTO)));
//        // 更新用户ip，最近登录时间
//        updateUserInfo();
    }
//
//    /**
//     * 更新用户信息
//     */
//    @Async
//    public void updateUserInfo() {
//        UserAuth userAuth = User.builder()
//                .id(UserUtils.getLoginUser().getId())
//                .ipAddress(UserUtils.getLoginUser().getIpAddress())
//                .ipSource(UserUtils.getLoginUser().getIpSource())
//                .lastLoginTime(UserUtils.getLoginUser().getLastLoginTime())
//                .build();
//        userAuthDao.updateById(userAuth);
//    }

}
