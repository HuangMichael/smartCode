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

import java.util.List;

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
        return users;
    }

    /**
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        log.info("删除----------------"+id);
        userService.delete(id);
        return "删除成功";
    }

}
