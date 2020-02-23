package com.learn.home.testng;

import org.testng.annotations.Test;

/**
 * 超时测试
 * @Test(timeOut = 3000)
 * 使用场景：一个测试，预计3秒中，假设超过5秒就不用等待，跳过该用例；
 */
public class TimeOutTest {

    //3000 的单位是毫秒
    @Test(timeOut = 3000)
    public  void testSuccess() throws InterruptedException{
        Thread.sleep(2000);
    }


    @Test(timeOut = 2000)
    public  void testFailed() throws InterruptedException{
        Thread.sleep(3000);
    }


}
