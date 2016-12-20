package com.bill.controller.user;

import com.bill.controller.BaseController;
import com.bill.domain.user.User;
import com.bill.service.user.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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


    /**
     * 根据用户id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }


    /**
     * @param id 根据id删除
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "删除";
    }



    /**
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll() {
      return  userService.findAll();

    }
}
