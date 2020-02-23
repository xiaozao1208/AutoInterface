package com.learn.home.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * 套件测试
 * 在resources下面新建一个 suite.xml文件，
 * 本次操作涉及文件：suite.xml,SuiteConfig.class,LoginTest.class;
 *
 * suite.xml 标准的xml文件，需要配置下；
 * suite.xml 运行结果顺序：
 * beforeSuite 运行拉
 * beforetest 运行拉
 * 淘宝登录成功
 * afterTest 运行拉
 * beforetest 运行拉
 * 支付宝支付成功
 * afterTest 运行拉
 * afterSuite 结束运行拉
 */
public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite 运行拉");
    }

    @AfterSuite
    public  void afterSuite(){
        System.out.println("afterSuite 结束运行拉");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforetest 运行拉");
    }

    @AfterTest
    public void  afterTest(){
        System.out.println("afterTest 运行拉");
    }

}
