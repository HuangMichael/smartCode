package com.bill.controller.resource;

import com.bill.controller.BaseController;
import com.bill.domain.resource.Resource;
import com.bill.service.resource.ResourceSearchService;
import com.bill.service.resource.ResourceService;
import com.bill.utils.page.MyPage;
import com.bill.utils.page.PageUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@EnableAutoConfiguration
@Data
@RequestMapping("/resource")
public class ResourceController extends BaseController {
    String mainObject = "resource";

    @Autowired
    ResourceService resourceService;

    @Autowired
    ResourceSearchService resourceSearchService;

    @Override
    public Boolean getViewOverride() {

        return true;
    }


    /**
     * @param pageable 分页参数
     * @return 分页查询所有资源
     */
    Page<Resource> findAll(Pageable pageable) {
        return resourceService.findAll(pageable);
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
        return new PageUtils().searchBySortService(resourceSearchService, searchPhrase, 2, current, rowCount, pageable);

    }
}
