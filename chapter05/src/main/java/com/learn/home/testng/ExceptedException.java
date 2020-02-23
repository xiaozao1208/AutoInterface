package com.learn.home.testng;

import org.testng.annotations.Test;

/**
 * 异常测试
 * 使用场景：在我们期望结果为某一个异常的时候。
 * 比如：我们传入了某些不合法的参数，程序抛出了异常； 也就是我们预期的结果就是个异常；
 *
 * 实现： @Test(expectedExceptions = RuntimeException.class)  异常类的名字
 * runTimeExceptionSuccess 方法为正确的写法
 */
public class ExceptedException {

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }

    //这个方法才是正确的写法；添加 throw new RunTimeException ;
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这个是我的异常测试");
        throw new RuntimeException();
    }
}
