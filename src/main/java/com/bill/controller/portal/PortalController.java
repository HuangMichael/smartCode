package com.bill.controller.portal;

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
@RequestMapping("/portal")
public class PortalController extends BaseController {

    final String mainObject = "portal";

}
