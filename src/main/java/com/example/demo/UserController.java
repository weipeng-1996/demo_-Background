package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.pojo.IMoocJSONResult;
import java.util.Date;
import com.example.pojo.User;

//@Controller
@RestController //RestController = @Controller + @ResponseBody
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUser")
//    @ResponseBody
    public User getUser(){
        User u = new User();
        u.setName("55555");
        u.setAge(18);
        u.setBirthday(new Date());
        u.setPassword("imooc");
        u.setDesc("hello_wpwp");
        return u;
    }
    @RequestMapping("/getUserJson")
//    @ResponseBody
    public IMoocJSONResult getUserJson(){
        User u = new User();
        u.setName("1111111");
        u.setAge(18);
        u.setBirthday(new Date());
        u.setPassword("imooc");
        u.setDesc("hello wp");
        return IMoocJSONResult.ok(u);
    }

}
