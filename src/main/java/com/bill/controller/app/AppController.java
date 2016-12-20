package com.bill.controller.app;

import com.bill.controller.BaseController;
import com.bill.domain.user.User;
import com.bill.service.app.AppSearchService;
import com.bill.service.app.AppService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

@Controller
@EnableAutoConfiguration
@Data
@RequestMapping("/app")
public class AppController extends BaseController {

    @Autowired
    AppService appService;
    String mainObject = "app";
    @Autowired
    AppSearchService appSearchService;
    @Autowired
    CommonDataService commonDataService;


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
        return new PageUtils().searchBySortService(appSearchService, searchPhrase, 2, current, rowCount, pageable);

    }


    /**
     * @return 生成应用
     */
    @RequestMapping(value = "/genApp", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject genApp(@RequestParam Long appId) {
        this.getLog().info("appId-------------------" + appId);
        Boolean result = appService.genApp(appId);
        return commonDataService.getReturnType(result, "应用生成成功!", "应用生成失败!");
    }


}
