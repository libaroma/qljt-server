/**
 * Created by Lib on 2022/5/31
 * Best Wishes !
 */

package com.office.qljt.qljtoffice.handler;

import com.google.gson.Gson;
import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.service.RedisService;
import com.office.qljt.qljtoffice.utils.IpUtils;
import com.office.qljt.qljtoffice.utils.LockUtils;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Component
public class ApiEncoderInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    private String ipAddress;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = handlerMethod.getMethod();
//            String name = method.getName();
//            //判断api是否加密验证
//            UnEncodeMethod unEncodeMethod = handlerMethod.getMethodAnnotation(UnEncodeMethod.class);
//
//            if (unEncodeMethod != null) {
//                //没有加密，不验证
//                //检查是否频繁提交
//                return handledCheckTooFrequentCommitMethod(handlerMethod, request, response, name);
//            } else {
//                //加密了，需要验证
//                return handledEncodeMethod(handlerMethod, request, response, name);
//            }
//        }
//        return false;
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

    /**
     * 验证频繁提交
     *
     * @param handlerMethod
     * @param request
     * @param response
     * @param name
     * @return
     */
    private boolean handledCheckTooFrequentCommitMethod(HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response, String name) {

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
                writeFailed(response, "请求过于频繁，请稍后重试！");
                return false;
            }
        }
        //api不需要验证频繁提交
        return true;
    }

    /**
     * 验证加密
     *
     * @param handlerMethod
     * @param request
     * @param response
     * @param name
     * @return
     * @throws Exception
     */
    private boolean handledEncodeMethod(HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response, String name) throws Exception {
        String time = request.getHeader("time");
        String value = request.getHeader("value");
        String ran = request.getHeader("ran");
        String rul = request.getHeader("rul");
        String url = request.getHeader("url");
        boolean verify = LockUtils.verify(value, url, ran, rul, time);
        if (!verify) {
            writeFailed(response, "编码已失效，请重新生成编码！");
            return false;
        }
        return handledCheckTooFrequentCommitMethod(handlerMethod, request, response, name);
    }

    private void writeFailed(HttpServletResponse response, String message) {
        response.setContentType("application/json;charset=UTF-8");
        Result<Object> fail = Result.fail(message);
        String s = new Gson().toJson(fail);
        try {
            response.getWriter().print(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
