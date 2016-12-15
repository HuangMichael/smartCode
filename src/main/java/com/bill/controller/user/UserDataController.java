package com.bill.controller.user;

import com.bill.controller.BaseDataController;
import com.bill.domain.resource.Resource;
import com.bill.service.resource.ResourceService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequestMapping("/userData")
public class UserDataController extends BaseDataController {

    @Autowired
    ResourceService resourceService;

    /*×
    * 查询菜单信息
    * */
    public Iterable<Resource> findMenus() {

        return resourceService.findAll();
    }

}
