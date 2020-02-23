package com.learn.home.testng;

import org.testng.annotations.Test;

/**
 * 依赖测试(测试方法的依赖)
 * @Test(dependsOnMethods = "依赖的方法名")
 * 如果依赖的方法运行成功，则test2也运行成功；
 * 反之，依赖的方法异常失败，则test2则被忽略，不执行。
 *
 * 使用场景：依赖是某些场景的前置条件；
 */
public class DependTest {

    @Test
    public void  test1(){
        System.out.println("test1 run!!!!");
//        throw new RuntimeException();
    }

    @Test(dependsOnMethods = "test1")
    public void  test2(){
        System.out.println("test2 run!!");
    }

}
