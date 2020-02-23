package com.learn.home.testng.groups;

import org.testng.annotations.Test;

/**
 * GroupsOnClass1·3 测试的是类分组测试；
 * 在整个类上面加 @Test(groups ="xxx")
 *
 * 会在resources下面创建一个 groupsOnClass.xml，用这个xml文件驱动；
 *
 *  <test name="onlyRunStu">
 *         <groups>
 *             <run>
 *                 <include name="stu"/>
 *             </run>
 *         </groups>
 *  (groups 这部分是类分组的关键配置内容)
 *         <classes>
 *             <class name="com.learn.home.testng.groups.GroupsOnClass1"/>
 *             <class name="com.learn.home.testng.groups.GroupsOnClass2"/>
 *             <class name="com.learn.home.testng.groups.GroupsOnClass3"/>
 *         </classes>
 *     </test>
 */

@Test(groups ="stu")
public class GroupsOnClass1 {
    public  void stu1(){
        System.out.println("GroupsOnClass1中的stu1的运行");
    }

    public  void stu2(){
        System.out.println("GroupsOnClass1中的stu2的运行");
    }
}
