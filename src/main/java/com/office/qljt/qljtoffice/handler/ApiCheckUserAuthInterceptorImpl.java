/**
 * Created by Lib on 2022/5/31
 * Best Wishes !
 */

package com.office.qljt.qljtoffice.handler;

import com.alibaba.fastjson.JSON;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.utils.UserUtils;
import com.office.qljt.qljtoffice.vo.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.office.qljt.qljtoffice.constant.CommonConst.APPLICATION_JSON;

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
                UserDTO userDTO = UserUtils.getLoginUser();
                if (userDTO == null) {
                    response.setContentType(APPLICATION_JSON);
                    response.getWriter().write(JSON.toJSONString(Result.fail("用户未登录")));
                    return false;
                } else if (userDTO.getRole() < checkUserAuth.role()) {
                    response.setContentType(APPLICATION_JSON);
                    response.getWriter().write(JSON.toJSONString(Result.fail("无权限操作")));
                    return false;
                }
            }
        }
        //api不需要权限
        return true;
    }

}
