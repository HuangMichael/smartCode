package com.bill.controller.user;

import com.bill.controller.BaseController;
import com.bill.domain.user.User;
import com.bill.service.user.UserSearchService;
import com.bill.service.user.UserService;
import com.bill.utils.page.CommonDataService;
import com.bill.utils.page.MyPage;
import com.bill.utils.page.PageUtils;
import com.bill.utils.page.ReturnObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@EnableAutoConfiguration
@Data
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    UserSearchService userSearchService;


    @Autowired
    CommonDataService commonDataService;
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
    public ReturnObject delete(@PathVariable Long id) {
        userService.delete(id);
        return commonDataService.getReturnType(userService.findById(id) != null, "用户信息删除成功!", "用户信息删除失败!");
    }


    /**
     * @return 查询所有的用户信息
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }


    /**
     * 分页查询
     *
     * @param current      当前页
     * @param rowCount     每页条数
     * @param searchPhrase 查询关键字
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public MyPage data(HttpServletRequest request, @RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Pageable pageable = new PageRequest(current - 1, rowCount.intValue(), super.getSort(parameterMap));
        return new PageUtils().searchBySortService(userSearchService, searchPhrase, 2, current, rowCount, pageable);
    }

    /**
     * @return 查询所有的id
     */
    @RequestMapping(value = "/findAllId", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> findAllId() {
        return userSearchService.findAllId();
    }
}
