package com.learn.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 1.一个get请求
 * 2.一个携带cookie的get请求；
 */
@RestController
@Api(value = "/",description="这是我的全部get方法")
public class MyGetMethod {

    /**
     *这个方法是从相应中取出cookies；
     */
    @RequestMapping(value="/getCookies",method= RequestMethod.GET)
    @ApiOperation(value="取出cookies的方法",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServletRequest   装请求信息的类 Server这个地方的写法注意下；
        //HttpServletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功222";
    }

    /**
     * 要求客户端携带cookie访问
     * 这是一个需要携带cookies信息才能访问的get请求
     */
    @RequestMapping(value="/get/with/cookies",method=RequestMethod.GET)
    @ApiOperation(value="需要携带cookies信息才能访问的get请求",httpMethod = "GET")
    public  String getWithCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "cookies参数为空，没有携带";
        }
        for(Cookie cookie :cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return "恭喜你访问成功";
            }
        }

        return "你必须携带cookies信息来！！";
    }

    /**
     * 开发一种需要携带参数才能访问的get请求
     * 第一种实现方式url： key=value&key=value  此处模拟商品价格
     * http://localhost:8889/get/with/param?start=10&end=20
     */
    @RequestMapping(value="/get/with/param",method=RequestMethod.GET)
    @ApiOperation(value="带参数访问的get请求第一种",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList= new HashMap<>();
        myList.put("鞋子",400);
        myList.put("方便面",1);
        myList.put("衬衫",300);

        return myList;
    }

    /**
     *第二种写法：http://localhost:8889/get/with/param/10/20
     */
    @RequestMapping(value="/get/with/param/{start}/{end}")
    @ApiOperation(value="带参数访问的get请求第二种",httpMethod = "GET")
    public Map<String,Integer> getList2(@PathVariable Integer start,
                                        @PathVariable Integer end){

        Map<String,Integer> MyList= new HashMap<>();
        MyList.put("鞋子",400);
        MyList.put("方便面",1);
        MyList.put("衬衫",300);

        return MyList;
    }
}
