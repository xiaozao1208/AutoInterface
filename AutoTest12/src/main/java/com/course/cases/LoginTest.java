package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    /**
     * 测试前准备工作，创建defaultHttpClient，获取url;
     */
    @BeforeTest(groups ="loginTrue",description="测试准备工作")
    public  void  beforeTest(){

        TestConfig.defaultHttpClient = new DefaultHttpClient();

        TestConfig.loginUrl= ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl=ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

    }


    @Test(groups="loginTrue",description = "用户登录成功的接口")
    public  void  loginTrue() throws IOException, InterruptedException {
        //执行SQLmapper中的SQL语句；
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);

        //输出SQL执行内容和13章的登录地址，将sql查询结果作为入参；
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        String result = getResult(loginCase);
        //为避免结果还未执行结束，停止3秒中；
        Thread.sleep(3000);
        System.out.println("12章数据库读取数据作为入参，请求13章的接口的结果："+result);

        //响应断言结果与预期是否一致
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    @Test(description = "用户登录失败的接口")
    public  void  loginFalse() throws IOException {
        SqlSession session  = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",2);

        //输出SQL执行内容和13章的登录地址，将sql查询结果作为入参；
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //调用getResult方法获取结果
        String result = getResult(loginCase);
        //打印结果并且断言比较
        System.out.println(result);
        Assert.assertEquals(loginCase.getExpected(),result);
    }



    //获取调用结果的方法 getResult
    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");

        //将参数信息添加到方法中,重要此步骤容易忘记
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("12章数据库读取数据作为入参，请求13章的接口的结果："+result);

        //获取cookie
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }

}
