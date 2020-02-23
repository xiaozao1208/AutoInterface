package com.learn.home.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * 组测试中的分组测试---类中的方法分组；
 *  将一个类中的多个方法，进行分组，进行的测试；
 *  关键词 @Test(groups ="XX") 根据groups的名称进行分组
 */
public class GroupsOnMethod {

    @Test(groups ="server")
    public void  test1(){
        System.out.println("这个是服务端的测试方法11111");
    }

    @Test(groups ="server")
    public void  test2(){
        System.out.println("这个是服务端的测试方法2222");
    }

    @Test(groups ="client")
    public void  test3(){
        System.out.println("这个是客户端的测试方法33333");
    }

    @Test(groups ="client")
    public void  test4(){
        System.out.println("这个是客户端的测试方法44444");
    }

    //beforeGroups("XX") 使用时，需要将group组的名字代号放进去
    @BeforeGroups("server")
    public void BeforeGroupsOnServer(){
        System.out.println("服务端：这个是在测试组运行之前运行的");
    }

    @AfterGroups("server")
    public  void  AfterGroupsOnServer(){
        System.out.println("服务端：这个是在测试组运行之后运行的");
    }

    @BeforeGroups("client")
    public void BeforeGroupsOnClient(){
        System.out.println("客户端：这个是在测试组运行之前运行的");
    }

    @AfterGroups("client")
    public  void  AfterGroupsOnClient(){
        System.out.println("客户端：这个是在测试组运行之后运行的");
    }
}


