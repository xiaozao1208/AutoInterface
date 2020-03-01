package com.course.controller;


import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * 1. 添加等待时间 Thread.sleep(2000);
 * 2. sql的顺序问题;
 * 3.@Autowired(这个备注没有添加   接口报错:)
 *   private SqlSessionTemplate  template;
 *   {
 *   "content-type": "application/json;charset=UTF-8",
 *   "date": "Fri, 21 Feb 2020 03:03:42 GMT",
 *   "transfer-encoding": "chunked"
 * }
 * */

@Log4j
@RestController
@Api(value="v1",description = "用户管理系统")
@RequestMapping("v1")
public class UserManager {


    @Autowired
    private SqlSessionTemplate  template;

    /**
     *登录接口 localhost:8081/v1/login
     */
    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ApiOperation(value="登录接口",httpMethod="POST")
    public boolean login(HttpServletResponse response, @RequestBody User user)  {

        int i = template.selectOne("login",user);
        Cookie cookie= new Cookie("login","true");
        response.addCookie(cookie);
        log.info("查询到的结果是"+i);

        if(i == 1){
            log.info("登录的用户是："+user.getUserName());
            return true;

        }
        return  false;
    }

    /**
     *添加用户接口
     * bug: User   单词拼写错误 Uesr  localhost:8081/addUser
     */
    @RequestMapping(value="/addUser",method=RequestMethod.POST)
    @ApiOperation(value="添加用户接口",httpMethod = "POST")
    public  boolean  addUser(HttpServletRequest request,@RequestBody User user){

        Boolean x =verIfyCookies(request);
        int result = 0;

        if(x != null){
               result = template.insert("addUser",user);
        }

        if(result > 0 ){
            log.info("添加用户的数量是："+result);
            return true;
        }
        return false;

    }

    /**
     *获取用户列表接口 localhost:8081/getUserInfo
     */
    @RequestMapping(value="/getUserInfo",method=RequestMethod.POST)
    @ApiOperation(value="获取用户列表接口",httpMethod = "POST")
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){

        Boolean x =verIfyCookies(request);

        if(x==true){
            List<User> users = template.selectList("getUserInfo",user);
            return users;
        }else{
            return null;
        }
    }


    /**
     *更新修改接口,删除接口  localhost:8081/v1/updateUserInfo
     */
    @RequestMapping(value="/updateUserInfo",method = RequestMethod.POST)
    @ApiOperation(value="更新/删除用户信息接口",httpMethod = "POST")
    public int updateUser(HttpServletRequest request,@RequestBody User user){

        Boolean x = verIfyCookies(request);

        int i=0;
        if(x==true){
            i = template.update("updateUserInfo",user);
        }
        log.info("更新数据的条目数为："+i);
        return i ;
    }


    private Boolean verIfyCookies(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("cookies为空");
            return  false;
        }

        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login")
            && cookie.getValue().equals("true")){
                log.info("cookies验证通过");
                return true;
            }
        }

        return false;
    }
}





















