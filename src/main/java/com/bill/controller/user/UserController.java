package com.bill.controller.user;

import com.bill.controller.BaseController;
import com.bill.domain.user.User;
import com.bill.service.user.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@EnableAutoConfiguration
@Data
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;
    String mainObject = "user";

    @Override
    public Boolean getViewOverride() {

        return true;
    }

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    public String save(User user) {
        user = new User();
        user.setUserName("test" + new Random().nextInt());
        user.setPassword("123456");
        user.setStatus("1");
        userService.save(user);
        return "success";
    }
}
