package com.learn.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    //这个是最基本的注释
    @Test
    public void  testCase1(){
        System.out.println("测试用例1");
    }

    @Test
    public void testCase2(){
        System.out.println("这个测试用例2");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforetest");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("aftertest");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod:这个是在测试方法运行之前运行的");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod:这个是在测试方法运行之后运行的");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("BeforeClass: 这个是在类运行之前运行的");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("AfterClass: 这个是在类运行之后运行的");
    }

    @BeforeSuite
    public void beforeSuit(){
        System.out.println("BeforeSuite:测试前套件");
    }

    @AfterSuite
    public  void  afterSuit(){
        System.out.println("afterSuit: 测试后套件");
    }
}

