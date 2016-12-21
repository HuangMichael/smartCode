package com.bill.controller;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by huangbin on 16-12-10.
 */
@Controller
@Data
@EnableAutoConfiguration
public class BaseController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private String mainObject = "";
    private Boolean viewOverride = false;
    private String viewPath = "/views/";


    @RequestMapping("/list")
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("appName", this.getMainObject());
        log.info(this.getMainObject() + "-----this is list request");
        viewPath = "/views/" + this.getMainObject() + "/list";
        return viewPath;
    }


    @RequestMapping("/")
    private String index(ModelMap modelMap) {
        log.info(this.getMainObject() + "-----this is index request");
        if (this.getViewOverride()) {
            viewPath += mainObject;
        }
        return viewPath + "/index";
    }

    @RequestMapping("/login")
    private String login(ModelMap modelMap) {
        log.info(this.getMainObject() + "-----this is login request");
        return "/views/login";
    }


    /**
     * @param requestMap
     * @return 获取排序的map
     * 第0位为排序顺序  asc desc
     * 第1位为排序字段
     */
    public Sort getSort(Map<String, String[]> requestMap) {
        String sortName = "id";
        Sort.Direction direction = Sort.Direction.ASC; //默认升序排列
        String directionStr = "asc";
        for (String key : requestMap.keySet()) {
            //如果key中以sort开头 获取它的值
            if (key.startsWith("sort")) {
                sortName = (key != null) ? key.split("\\[|\\]")[1] : sortName;
                directionStr = (requestMap.get(key)[0] != null) ? requestMap.get(key)[0] : directionStr;
                break;
            }
        }
        if (directionStr.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Sort sort = new Sort(direction, sortName);
        return sort;
    }

}
