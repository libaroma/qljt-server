package com.office.qljt.qljtoffice.config;


import com.office.qljt.qljtoffice.handler.ApiCheckTooFrequentCommitInterceptorImpl;
import com.office.qljt.qljtoffice.handler.ApiCheckUserAuthInterceptorImpl;
import com.office.qljt.qljtoffice.handler.ApiEncoderInterceptorImpl;
import com.office.qljt.qljtoffice.handler.PageableHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web mvc配置
 *
 * @author lib
 * @date 2021/07/29
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowedMethods("*");
    }


    @Autowired
    private ApiCheckTooFrequentCommitInterceptorImpl checkTooFrequentCommitInterceptor;

    @Autowired
    private ApiCheckUserAuthInterceptorImpl checkUserAuthInterceptor;

    @Autowired
    private ApiEncoderInterceptorImpl apiEncoderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //api请求频繁过滤
        registry.addInterceptor(checkTooFrequentCommitInterceptor);
        //api请求权限过滤
        registry.addInterceptor(checkUserAuthInterceptor);
        //api请求加密过滤
        registry.addInterceptor(apiEncoderInterceptor);
        //分页拦截
        registry.addInterceptor(new PageableHandlerInterceptor());
    }


}
