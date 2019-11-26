package com.arz.pmp.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * description 项目启动类
 * 
 * @author chen wei
 * @date 2019/11/11
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.arz.pmp"})
@MapperScan({"com.arz.pmp.base.mapper", "com.arz.pmp.base.exmapper"})
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }
}
