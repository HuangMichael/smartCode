package com.bill.service.app;

import com.bill.domain.app.App;
import com.bill.repository.app.AppRepository;
import com.bill.repository.user.UserRepository;
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

    /**
     * @param pageable
     * @return 查询所有的应用信息
     */
    public Page<App> findAll(Pageable pageable) {

        return appRepository.findAll(pageable);
    }


}
