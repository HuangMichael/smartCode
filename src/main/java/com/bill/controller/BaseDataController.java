package com.bill.controller;

import com.bill.service.BaseService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by huangbin on 16-12-10.
 */
@RestController
@Data
@NoArgsConstructor
public class BaseDataController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    private String mainObject = "";

    @Autowired
    BaseService baseService;
    public BaseDataController(BaseService baseService) {
        this.baseService = baseService;
    }

}
