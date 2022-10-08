/**
 * Created by Lib on 2022/5/31
 * Best Wishes !
 */

package com.office.qljt.qljtoffice.handler;

import com.office.qljt.qljtoffice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiEncoderInterceptorImpl implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    private String ipAddress;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            //判断api是否加密验证
//            UnEncodeMethod unEncodeMethod = handlerMethod.getMethodAnnotation(UnEncodeMethod.class);
//
//            if (unEncodeMethod == null) {
//                //加密了，需要验证
//                String time = request.getHeader("time");
//                String value = request.getHeader("value");
//                String ran = request.getHeader("ran");
//                String rul = request.getHeader("rul");
//                String url = request.getHeader("url");
//                boolean verify = LockUtils.verify(value, url, ran, rul, time);
//                if (!verify) {
//                    throw new BizException("编码已失效，请重新生成编码！");
//                }
//            }
//        }
        return true;
    }
}
