package com.course.controller;


import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Api(value="v1",description="这个第一个版本的demo")
//@RequestMapping("v1")
//api处的value与requestMapping处的value值是一致的；

@Log4j
@RestController
@Api(value="/v1",description="这个第一个版本的demo")
@RequestMapping("/v1")
public class demo {

    //首先获取一个执行sql的语句对象

    @Autowired
    private SqlSessionTemplate template;

    /**
     * http://localhost:8886/v1/getUserCount  返回值是4条;
     * @return
     */
    @RequestMapping(value="/getUserCount",method= RequestMethod.GET)
    @ApiOperation(value="获取用户数",httpMethod="GET")
    public int getUserCount(){
        return template.selectOne("getUserCount");
    }

    /**
     * http://localhost:8886/v1/getUserCount
     */
    @RequestMapping(value="/addUser",method=RequestMethod.POST)
    public int addUser(@RequestBody User user){
        //学习使用debug
        int result = template.insert("addUser",user);
        return  result;
    }


    @RequestMapping(value="/updateUser",method=RequestMethod.POST)
    public  int  updateUser(@RequestBody User user){
        int result = template.update("updateUser",user);
        return  result;

    }


    @RequestMapping(value="/deleteUser",method=RequestMethod.GET)
    public int deleteUser(@RequestParam int id){
        int result  = template.delete("deleteUser",id);
        return result;

    }
}
