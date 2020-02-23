package com.learn.home.testng.parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 参数化测试方法1：通过xml文件配置参数；
 * 参数化测试方法2：Dataprovider提供；
 * 格式：
 *     @Test
 *     @Parameters({"name","age"})
 * 格式配合：resources 中建立 parameters.xml
 *  <?xml version="1.0" encoding="UTF-8" ?>
 * <suite name="parameter">
 *     <test name="param">
 *         <classes>
 *             <parameter name="name" value="zhangsan"/> //关键
 *             <parameter name="age" value="10"/> //关键
 *             <class name="com.learn.home.testng.parameter.ParamterTest"/>
 *         </classes>
 *     </test>
 * </suite>
 */
public class ParamterTest {

    @Test
    @Parameters({"name","age"})
    public void paramterTest1(String name , int age){
        System.out.println("name="+name+"; age="+age);
    }
}
