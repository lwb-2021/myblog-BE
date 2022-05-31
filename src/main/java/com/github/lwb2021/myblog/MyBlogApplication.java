package com.github.lwb2021.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.github.lwb2021.myblog.mapper")
public class MyBlogApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(MyBlogApplication.class, args);
    }

}
