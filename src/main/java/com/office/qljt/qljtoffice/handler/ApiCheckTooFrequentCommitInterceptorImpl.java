/**
 * Created by Lib on 2022/5/31
 * Best Wishes !
 */

package com.office.qljt.qljtoffice.handler;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.exception.BizException;
import com.office.qljt.qljtoffice.service.RedisService;
import com.office.qljt.qljtoffice.utils.IpUtils;
import com.office.qljt.qljtoffice.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ApiCheckTooFrequentCommitInterceptorImpl implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    private String ipAddress;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            String name = method.getName();
            //检查是否频繁提交
            //判断api是否验证频繁提交
            CheckTooFrequentCommit checkTooFrequentCommit = handlerMethod.getMethodAnnotation(CheckTooFrequentCommit.class);
            if (checkTooFrequentCommit != null) {
                //api需要验证频繁提交
                // 获取用户ip
                if (TextUtils.isEmpty(ipAddress)) {
                    ipAddress = IpUtils.getIpAddress(request);
                }
                String key = ipAddress + "_" + name;
                String rec = (String) redisService.get(key);

                if (TextUtils.isEmpty(rec)) {
                    //没有记录：放行
                    return true;
                } else {
                    //有记录：拦截
                    throw new BizException("请求过于频繁，请稍后重试！");
                }
            }
        }
        //api不需要验证频繁提交
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //判断api是否验证频繁提交
            CheckTooFrequentCommit checkTooFrequentCommit = handlerMethod.getMethodAnnotation(CheckTooFrequentCommit.class);
            if (checkTooFrequentCommit != null) {
                Method method = handlerMethod.getMethod();
                String name = method.getName();
                // 获取用户ip

                if (TextUtils.isEmpty(ipAddress)) {
                    ipAddress = IpUtils.getIpAddress(request);
                }
                String key = ipAddress + "_" + name;
                String rec = (String) redisService.get(key);
                if (TextUtils.isEmpty(rec)) {
                    //没有记录：写入
                    redisService.set(key, "1", 10);
                }
            }
        }
    }

}
