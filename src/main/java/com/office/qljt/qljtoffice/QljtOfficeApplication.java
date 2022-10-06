package com.office.qljt.qljtoffice;

import com.office.qljt.qljtoffice.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@MapperScan("com.office.qljt.qljtoffice.dao")
@SpringBootApplication
@EnableScheduling
public class QljtOfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(QljtOfficeApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IdWorker createIdWorker(){
        return new IdWorker(0,0);
    }

}
