package com.learn.home.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * 参数化测试方法2：Dataprovider提供；
 *  @Test(dataProvider = "data")
 *
 *  第一种用法：直接通过一个方法dataprovider传递参数；
 *  第二种用法：通过传递不同的方法名，传递不同的参数；
 */
public class DataProviderTest {
    //dataprovider 的第一种用法
    @Test(dataProvider = "data")
    public  void testDataProvider(String name, int age){
        System.out.println("name ="+name+"; age="+age);
    }

    @DataProvider(name="data")
    public  Object[][]  providerData(){
        Object[][]  obj =new Object[][]{
                {"zhangsan",10},
                {"lisi",20},
                {"liming",30}
        };
        return obj;
    }


    //测试2-dataprovider的第二种用法
    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.println("test1的方法：name="+name+"; age="+age);
    }

    @Test(dataProvider = "methodData")
    public  void test2(String name,int age){
        System.out.println("test2的方法：name="+name+"; age="+age);
    }

    //import java.lang.reflect.Method; method导入的包的注意事项
    @DataProvider(name="methodData")
    public  Object[][]  methodDate(Method method){
        Object[][] result = null ;

        if(method.getName().equals("test1")){
            result = new Object[][]{
                    {"zhangsan",20},
                    {"lisi",25}
            };
        }else if(method.getName().equals("test2")){
            result =new Object[][]{
                    {"huge",35},
                    {"yuanhong",40}
            };
        }

        return result;
    }

}
