package com.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

/**
 * 1.填坑注意：application启动失败，主要是导入的jar包和maven依赖的包混乱所致；以后导入包时候要注意；
 * 2.Failed to obtain JDBC Connection
     * mysql.xml:配置文件
     *--select  查询时使用；
     *id唯一即可，与方法中的对应起来
     *resultType 是返回结果的数据类型，这里是Integer-->
 * 3.select count(*) from test.emp;(此处的写法是test.emp)
 4.application.yml中此处配置修改：
 * mybatis:
 *   type-aliases-package: com.course.model
 *   mapper-locations: - mapper/*
 *    修改为： mapper-locations: /mapper/*.xml
 */
/*到处好用的application*/
@EnableScheduling
@SpringBootApplication
public class Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.context = SpringApplication.run(Application.class, args);
    }

    //预存储
    @PreDestroy
    public void close() {
        Application.context.close();
    }
}

