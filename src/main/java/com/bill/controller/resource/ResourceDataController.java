package com.bill.controller.resource;

import com.bill.controller.BaseDataController;
import com.bill.domain.resource.Resource;
import com.bill.service.resource.ResourceService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequestMapping("/resourceData")
public class ResourceDataController extends BaseDataController {

    @Autowired
    ResourceService resourceService;

    private String mainObject = "resource";


    /**
     * 查询所有的数据
     */
    @RequestMapping("/findAll")

    public Iterable findAll() {
        return resourceService.findAll();
    }

    /**
     * 查询菜单信息
     */

    @RequestMapping("/findMenus/{level}")
    public Iterable<Resource> findMenus(@PathVariable Long level) {
        return resourceService.findMenus(level);
    }

}
