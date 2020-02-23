package com.learn.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * application.properties里面可以配置多个路径，包括访问路径，cookies路径，多个配置均可以在此配置；
 * java.util.ResourceBundle  bundle作用：读取资源属性文件（properties）
 */
public class MyCookiesForGet {

    private String  url;
    private ResourceBundle bundle;

    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        //这里小坑注意： Locale.CHINA 中文字符编码
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url =bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        //从配置文件中 拼接测试的url
        String uri=bundle.getString("getCookies.uri");
        String  testUrl = this.url+uri;

        //测试逻辑代码书写
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);

        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList =store.getCookies();

       for(Cookie cookie: cookieList) {
           String name = cookie.getName();
           String value = cookie.getValue();
           System.out.println("cookie name=" + name + ";cookie value=" + value);
       }


    }

    @Test(dependsOnMethods = "testGetCookies")
    public  void testGetwithCookies() throws IOException {
        String uri = bundle.getString("test.get.with.cookies");
        String testUrl = this.url+uri;
        HttpGet get= new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookies信息
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);

        //获取相应我的状态码
        int  statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        if(statusCode ==200){
            String result =EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }
    }
}
