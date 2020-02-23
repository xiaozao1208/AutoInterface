package com.reports.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 1.常用的三种测试报告
 1）TestNG自带的测试报告
 2）ReportNg测试报告展示
 3）ExtentReport测试报告页面  官网：extentreports.com(本章学习此；)
 2.html页面加载异常处理：
 //设置静态文件的DNS
 //怎么解决cdn.rawqit.com访问不了的情况       //htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);

 **在idea中配置ExtentReport测试报告的整体流程：
 01.pom.xml文件中添加ExtentReports三个配置
 <dependency>
 <groupId>com.relevantcodes</groupId>
 <artifactId>extentreports</artifactId>
 <version>2.41.1</version>
 </dependency>

 <dependency>
 <groupId>com.aventstack</groupId>
 <artifactId>extentreports</artifactId>
 <version>3.0.6</version>
 </dependency>

 <dependency>
 <groupId>com.vimalselvam</groupId>
 <artifactId>testng-extentsreport</artifactId>
 <version>1.3.1</version>
 </dependency>
 02.类中书写测试方法（可能多个） 本章写在TestMethodsDemo；
 03.resources下面 写xml测试套件文件执行测试方法report.xml和添加监听类listener；
 <suite name="自己的接口测试套件">
 <test name="这些是测试模块">
 <classes>
 <class name="com.reports.test.TestMethodsDemo"/>
 <methods>
 <include name="test1"/>
 <include name="test2"/>
 <include name="test3"/>
 <include name="logoDemo"/>
 </methods>
 </classes>
 </test>
 <!-- 添加监听 打印测试报告，需要添加监听器 <listeners> </listeners>-->
 <!--listener 中 class-name 是工具类的路径-->
 <listeners>
 <listener class-name="com.reports.test.ExtentTestNGReporterListener"/>
 </listeners>
 </suite>
 04.监听类书写 ExtentTestNGReporterListener内容后期拷贝即可使用，放在测试类一起；
 05.执行测试套件配置文件report.xml后，从test-output中查看“index.html”复制路径查看即可；
 06.查看报告   正确和错误，错误的会展示错误原因，可供分析；
 */
public class TestMethodsDemo {

    @Test//这个是断言失败
    public void  test1(){
        Assert.assertEquals(1,2);
    }

    @Test//此方法是断言成功
    public void  test2(){
        Assert.assertEquals(1,1);
    }

    @Test//此方法是断言成功
    public void  test3(){
        Assert.assertEquals("aaa","aaa");
    }

    @Test//打印日志
    public void  logoDemo(){
        Reporter.log("这个是我自己写的日志");
        throw new RuntimeException("这个是我们自己的运行时异常");
    }
}
