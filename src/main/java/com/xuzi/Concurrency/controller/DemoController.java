package com.xuzi.Concurrency.controller;

import com.xuzi.Concurrency.domain.User;
import com.xuzi.Concurrency.redis.RedisService;
import com.xuzi.Concurrency.redis.UserKey;
import com.xuzi.Concurrency.result.Result;
import com.xuzi.Concurrency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","xiboy");
        return "hello";
    }

    @RequestMapping("/dbget")
    @ResponseBody
    public Result<User> dbGet(Model model){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/getredis")
    @ResponseBody
    public Result<String> getRedis(){
        String v1 = redisService.get(UserKey.getById,""+1,String.class);
        return Result.success(v1);
    }

    @RequestMapping("/settredis")
    @ResponseBody
    public Result<Integer> setRedis(){
        redisService.set(UserKey.getById,""+1,123);
        int v1 = redisService.get(UserKey.getById,""+1,int.class);
        return Result.success(v1);
    }

}
