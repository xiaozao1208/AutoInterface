package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    @Test(dependsOnMethods = "loginTrue",description="添加用户接口")
    public void  addUser() throws IOException, InterruptedException {
        SqlSession session  = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",1);

        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);
        //调取的时候是不同的线程，容易有问题，Thread.sleep(3000);

        String result  =getResult(addUserCase);
        Thread.sleep(3000);

        User user = session.selectOne("addUser",addUserCase);
        System.out.println(user.toString());

        Assert.assertEquals(addUserCase.getExpected(),result);


    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);

        JSONObject param = new JSONObject();
        param.put("userName",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());

        post.setHeader("content-type","application/json");

        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //少了一步：设置cookie信息
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;
        HttpResponse response =TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println("12章数据库读取数据作为入参，请求13章的接口的结果："+result);

        return result;

    }
}
