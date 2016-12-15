package com.bill.repository.resource;

import com.bill.domain.resource.Resource;
import com.bill.utils.constValue.CommonStateUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by huangbin on 16-12-10.
 * 用户信息数据库访问接口
 */
@CacheConfig(cacheNames = "resourcesCache")
public interface ResourceRepository extends CrudRepository<Resource, Long>, PagingAndSortingRepository<Resource, Long> {


    /**
     * 根据资源级别查询
     */
    Iterable<Resource> findByResourceLevel(Long resourceLevel);


    /**
     * 根据资源级别查询
     *
     * @param resourceLevel 菜单级别
     * @param status        状态
     * @return
     */
    Iterable<Resource> findByResourceLevelAndStatus(Long resourceLevel, Boolean status);


    /**
     * @param pageable
     * @return 查询所有的资源信息
     */
    Page<Resource> findAll(Pageable pageable);
}
