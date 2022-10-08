/**
 * Created by Lib on 2022/5/31
 * Best Wishes !
 */

package com.office.qljt.qljtoffice.handler;

import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.utils.UserUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.office.qljt.qljtoffice.constant.RoleTypeConst.ADMIN;

@Component
public class ApiCheckUserAuthInterceptorImpl implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //判断用户权限
            CheckUserAuth checkUserAuth = handlerMethod.getMethodAnnotation(CheckUserAuth.class);
            if (checkUserAuth != null) {
                //判断用户权限
                if (checkUserAuth.role() >= ADMIN) {
                    UserDTO userDTO = UserUtils.getLoginUser();
                    if (userDTO == null) return false;
                    return userDTO.getRole() >= checkUserAuth.role();
                }
            }
        }
        //api不需要权限
        return true;
    }

}
