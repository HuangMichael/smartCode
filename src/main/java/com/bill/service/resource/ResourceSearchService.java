package com.bill.service.resource;


import com.bill.domain.resource.Resource;
import com.bill.repository.resource.ResourceRepository;
import com.bill.service.BaseService;
import com.bill.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by huangbin on 2016/3/24.
 * 线路查询业务类
 */
@Service
public class ResourceSearchService extends BaseService implements SortedSearchable {

    @Autowired
    ResourceRepository resourceRepository;


    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public Iterable<Resource> findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return resourceRepository.findAll();
    }


    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public Page<Resource> findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return resourceRepository.findAll(pageable);
    }

}

