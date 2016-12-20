package com.bill.service.app;

import com.bill.coder.utils.SmartCoderUtil;
import com.bill.domain.app.App;
import com.bill.repository.app.AppRepository;
import com.bill.repository.user.UserRepository;
import com.bill.utils.codeBulider.modelBulider.ModelBuilder;
import com.bill.utils.page.CommonDataService;
import com.bill.utils.page.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by huangbin on 16-12-10.
 * 应用业务类
 */
@Service
public class AppService {
    @Autowired
    AppRepository appRepository;
    @Autowired
    CommonDataService commonDataService;

    @Autowired
    ModelBuilder modelBuilder;
    @Autowired
    SmartCoderUtil smartCoderUtil;

    /**
     * @param pageable
     * @return 查询所有的应用信息
     */
    public Page<App> findAll(Pageable pageable) {

        return appRepository.findAll(pageable);
    }


    /**
     * @param appId 应用id
     * @return 生成应用的model类
     */
    public Boolean genApp(Long appId) {
        App app = appRepository.findOne(appId);
        smartCoderUtil.createFile(app);
        try {
            modelBuilder.createFile("src\\main\\java\\com\\bill\\domain\\" + app.getPackageName(), app.getAppName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}
