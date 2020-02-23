package com.learn.home.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {

    public  void stu1(){
        System.out.println("GroupsOnClass2中的stu1的运行");
    }

    public  void stu2(){
        System.out.println("GroupsOnClass2中的stu2的运行");
    }
}
