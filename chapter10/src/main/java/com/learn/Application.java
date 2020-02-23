package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//写需要扫描的包
@SpringBootApplication
@ComponentScan("com.learn.server")
public class Application {

    public  static  void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

}
