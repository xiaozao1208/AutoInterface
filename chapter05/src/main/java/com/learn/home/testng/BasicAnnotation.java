package com.learn.home.testng;


import org.testng.annotations.*;

public class BasicAnnotation {

    /**
     * @Test  选择 add testNg to clsspath
     *        import org.testng.annotations.Test;
     *     此处关注的是各种testNG注释的执行顺序：
     *     suite 可以包含多个class； 一个class中可以包含多个测试方法；
     *     测试方法前后也有测试注释；
     *
     * 以下几个备注的测试执行顺序：
     *  beforeSuite: 测试套件
     * beforeclass: 这个是类运行之前运行的；
     * BeforeMethod: 这个是在测试方法之前运行的
     * 这是一个测试用例1
     * aftermethod:这个是在测试方法之后运行的
     * BeforeMethod: 这个是在测试方法之前运行的
     * 这个是测试方法2
     * aftermethod:这个是在测试方法之后运行的
     * afterclass: 这个是在类之后运行的
     * afterSuite:测试套件
     */
    @Test
    public void testCase1(){
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        System.out.println("这是一个测试用例1");
    }

    @Test
    public void testCase2(){
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        System.out.println("这个是测试方法2");
    }

    @BeforeMethod
    public void  BeforeMethod(){
        System.out.println("BeforeMethod: 这个是在测试方法之前运行的");
    }

    @AfterMethod
    public  void afterMethod(){
        System.out.println("aftermethod:这个是在测试方法之后运行的");
    }

    @BeforeClass
    public  void  beforeClass(){
        System.out.println("beforeclass: 这个是类运行之前运行的；");
    }

    @AfterClass
    public  void  afterClass(){
        System.out.println("afterclass: 这个是在类之后运行的");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite: 测试套件");
    }

    @AfterSuite
    public  void  afterSuite(){
        System.out.println("afterSuite:测试套件");
    }
}
