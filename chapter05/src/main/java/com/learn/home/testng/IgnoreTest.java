package com.learn.home.testng;

import org.testng.annotations.Test;

/**
 * 忽略测试
 * @Test(enabled = false) 此方法不执行，即忽略；
 * @Test 没有enabled 不写默认是true 执行的；
 *
 * 以下代码执行结果：
 * ignoretest1 执行；
 * ignoretest3 执行；
 */
public class IgnoreTest {

    @Test
    public  void  ignoreTest1(){
        System.out.println("ignoretest1 执行；");
    }

    @Test(enabled = false)
    public  void  ignoreTest2(){
        System.out.println("ignoretest2 执行；");
    }

    @Test(enabled = true)
    public  void  ignoreTest3(){
        System.out.println("ignoretest3 执行；");
    }

}
