package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
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

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "更改用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase",1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);


        int result = getResult(updateUserInfoCase);

        //获取更新后的结果
        Thread.sleep(5000);
        User user = session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        System.out.println(user.toString());

        //查出来数据不为空,结果也不为空,认为更新成功.
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);

    }

    @Test(dependsOnGroups = "loginTrue",description = "删除用户")
    public void deleteUser() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase",2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);


        int result = getResult(updateUserInfoCase);
        Thread.sleep(5000);
        /**
         * //updateUserInfoCase.getExpected()的结果是：getUpdateUserInfo 数据库写的；
         *         //此名称与 UserMapper.xml 中的id=getUpdateUserInfo 对应
         *         //相当于  User user =session.selectOne("getUpdateUserInfo",updateUserInfoCase);
          */

        User user = session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        System.out.println(user.toString());

        //查出来数据不为空,结果也不为空,认为更新成功.
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }


    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        //此处容易错误 param.put("id",updateUserInfoCase.getUserId());
        param.put("id",updateUserInfoCase.getUserId());
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("age",updateUserInfoCase.getAge());
        param.put("permission",updateUserInfoCase.getPermission());
        param.put("isDelete",updateUserInfoCase.getIsDelete());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return Integer.parseInt(result);

    }

}

