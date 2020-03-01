package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

    //定义一个读取url的参数，bundle
    private static ResourceBundle bundle
            = ResourceBundle.getBundle("application",Locale.CHINA);

    //这是一个获取url基础地址和拼接地址的方法
    public static String getUrl(InterfaceName name){

        //获取基础地址 http://localhost:8081
        String address = bundle.getString("test.url");

        String uri="";  //拼接的部分地址
        String testUrl;//最终的测试地址

        if(name == InterfaceName.LOGIN){
            uri =bundle.getString("login.uri");
        }
        if(name == InterfaceName.ADDUSERINFO){
            uri =bundle.getString("addUser.uri");
        }
        if(name == InterfaceName.GETUSERINFO){
            uri =bundle.getString("getUserInfo.uri");
        }
        if(name == InterfaceName.GETUSERLIST){
            uri =bundle.getString("getUserList.uri");
        }
        if(name == InterfaceName.UPDATEUSERINFO){
            uri=bundle.getString("updateUserInfo.uri");
        }

        testUrl = address + uri;
        return testUrl;
    }

}
