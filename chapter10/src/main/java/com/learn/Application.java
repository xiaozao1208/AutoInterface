package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//写需要扫描的包
//@ComponentScan("com.learn.server")这样写扫描不到 SwaggerConfig 配置文件，所以修改路径
//不知道为什么扫描报错，swagger和接口都没有问题； ？？
@ComponentScan("com.learn")
@SpringBootApplication
public class Application {

    public  static  void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

}

