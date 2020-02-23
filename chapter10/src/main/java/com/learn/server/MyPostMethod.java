package com.learn.server;


import com.learn.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value="这个是post",description="这个是我的全部post请求")
@RequestMapping("/v1")
public class MyPostMethod {

    //这个变量是用来装我们的cookies信息；
    private static Cookie cookie;

    //用户登录成功获取到cookies,然后再访问其他接口获取列表
    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ApiOperation(value="登录接口",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value="username",required= true) String username,
                        @RequestParam(value="password",required= true) String password){
        if(username.equals("lijuan") && password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登录成功了";
        }
        return "用户名或密码错误";
    }

    /**
     * Plugins-lombok-安装使用 @Data
     *
     * Jmeter中不展示cookies的处理办法是 在jmeter/bin中的jmeter.properties 修改配置文件：
     *      CookieManager.save.cookies=true，先前默认的是false,修改为true;
     */

    @RequestMapping(value="/getUserList",method=RequestMethod.POST)
    @ApiOperation(value="获取用户列表",httpMethod="POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u){
        //@RequestBody
        User user;
        //获取cookies信息
        Cookie[] cookies = request.getCookies();

        //验证cookies信息
        for(Cookie c :cookies){
            if(c.getName().equals("login")
                    && c.getValue().equals("true")
                    && u.getUsername().equals("lijuan")
                    &&  u.getPassword().equals("123456")
            ){

                user = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");

                return user.toString();

            }
        }

        return "参数不合法或错误！！！";
    }
}
