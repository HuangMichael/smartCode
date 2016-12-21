package com.bill.service.resource;

import com.bill.domain.resource.Resource;
import com.bill.repository.resource.ResourceRepository;
import com.bill.service.BaseService;
import com.bill.utils.constValue.CommonStateUtil;
import com.bill.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin on 16-12-10.
 * 资源业务信息
 */
@Service
public class ResourceService extends BaseService {
    @Autowired
    ResourceRepository resourceRepository;

    /**
     * @return 查询所有的资源信息
     */
    public Iterable findAll() {
        return resourceRepository.findAll();
    }


    /**
     * @param pageable 分页参数
     * @return 分页查询所有的资源信息
     */
    public Page<Resource> findAll(Pageable pageable) {

        return resourceRepository.findAll(pageable);
    }

    /**
     * @param level 菜单级别
     * @return 根据菜单级别查询资源信息
     */
    public Iterable<Resource> findMenus(Long level) {
        return resourceRepository.findByResourceLevelAndStatus(level, CommonStateUtil.STATUS_YES);
    }
}
