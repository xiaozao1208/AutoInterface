package com.learn.home.testng.multiThread;

import org.testng.annotations.Test;
/**
* 多线程测试实现
* 第一种：注解实现
* @Test(invocationCount = 3,threadPoolSize = 3)
* invocationCount----表示执行的次数
 * threadPoolSize-----表示线程池的内线程的个数,最多个数；
 * 第二种：xml文件实现
 *  在resources 创建文件 MultiThread.xml
 */
public class MultiThreadOnXml {

    @Test
    public void  test1(){
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void  test2(){
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void  test3(){
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
}
