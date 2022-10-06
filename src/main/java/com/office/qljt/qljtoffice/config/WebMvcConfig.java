package com.office.qljt.qljtoffice.config;


import com.office.qljt.qljtoffice.handler.ApiEncoderInterceptor;
import com.office.qljt.qljtoffice.handler.PageableHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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


    @Bean
    public ApiEncoderInterceptor getApiEncoderInterceptor() {
        return new ApiEncoderInterceptor();
    }

    @Autowired
    private ApiEncoderInterceptor apiEncoderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PageableHandlerInterceptor());
        //api请求过滤
        registry.addInterceptor(apiEncoderInterceptor);
    }



}
