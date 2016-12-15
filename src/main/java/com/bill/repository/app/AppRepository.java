package com.bill.repository.app;

import com.bill.domain.app.App;
import com.bill.domain.resource.Resource;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 应用信息数据库访问接口
 * @author huangbin
 */
@CacheConfig(cacheNames = "appsCache")
public interface AppRepository extends CrudRepository<App, Long>, PagingAndSortingRepository<App, Long> {


    /**
     * @param pageable
     * @return 分页查询所有的应用信息
     */
    Page<App> findAll(Pageable pageable);
}
