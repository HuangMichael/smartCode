package com.bill.controller;

import com.bill.domain.resource.Resource;
import com.bill.domain.user.User;
import com.bill.service.resource.ResourceService;
import com.bill.service.user.UserService;
import com.bill.utils.RedisUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@Data
@RequestMapping("/redis")
public class HelloController extends BaseDataController {
    String mainObject = "hello";
    @Autowired
    ResourceService resourceService;

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    private final Long EXPIRED_TIME = 3l;


    /**
     * @return
     */
    @RequestMapping("/resources")
    @ResponseBody
    public Iterable<Resource> findResources() {
        log.info("查询所有的资源resources----------------");
        Iterable<Resource> resources = resourceService.findMenus(1l);
        redisUtil.setList("resources", resources);
        Boolean exists = redisUtil.exists("resources");
        System.out.println("menus exists-------------" + exists);
        return resources;
    }


    /**
     * @return
     */
    @RequestMapping("/users")
    @ResponseBody
    public Iterable<User> findUsers() {
        log.info("查询所有的user资源----------------");
        Iterable<User> users = userService.findAll();
        redisUtil.setList("users", users);
        Boolean exists = redisUtil.exists("users");
        System.out.println("users exists-------------" + exists);
        return users;
    }


    /**
     * @return
     */
    @RequestMapping("/users/{userName}")
    @ResponseBody
    public Iterable<User> findByUserName(@PathVariable String userName) {
        log.info("查询所有的user资源----------------");
        Iterable<User> usersList = userService.findByUserName(userName);
        return usersList;
    }


}
