package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnMethods = "loginTrue",description = "通过userId 获取用户信息")
    public void getUsrInfo() throws IOException, InterruptedException {
        SqlSession session  = DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase",1);

        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        JSONArray resultJson = getResult(getUserInfoCase);

        Thread.sleep(3000);

        //getUserInfoCase.getExpected()的结果是：getUserInfo
        //此名称与 UserMapper.xml 中的id=getUserInfo 对应
        //相当于  User user =session.selectOne("getUserInfo",getUserInfoCase);
        User user =session.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
        System.out.println("自己查库获取用户信息:"+ user.toString());

        List userList = new ArrayList();
        userList.add(user);
        //把查询出来的User放进去
        JSONArray jsonArray = new JSONArray(userList);
        JSONArray resultJson1 = new JSONArray(resultJson.getString(0));
        System.out.println("获取用户信息:"+jsonArray.toString());
        System.out.println("调用接口获取用户信息:"+resultJson1.toString());

        //数据格式不对，导致断言错误；
        Assert.assertEquals(jsonArray.toString(),resultJson1.toString());

    }

    private JSONArray getResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post =new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param  =new JSONObject();
        param.put("id",getUserInfoCase.getUserId());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("12章数据库读取数据作为入参，请求13章的接口的结果："+result);
        /**
         * 转化成jsonarray
         */
        List resultList = Arrays.asList(result);
        JSONArray array = new JSONArray(resultList);
        System.out.println(array.toString());
        return array;
    }
}
