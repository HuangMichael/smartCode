package com.bill.service.user;


import com.bill.domain.user.User;
import com.bill.repository.user.UserRepository;
import com.bill.service.BaseService;
import com.bill.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 * 用户查询业务类
 */
@Service
public class UserSearchService extends BaseService implements SortedSearchable {

    @Autowired
    UserRepository userRepository;


    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public Iterable<User> findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return userRepository.findAll();
    }


    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public Page<User> findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return userRepository.findAll(pageable);
    }

    /**
     * @return 查询所有的id集合
     */
    @Cacheable(value = "personIdList", key = "'personIdList'")
    public List<Long> findAllId() {
        return userRepository.findAllId();

    }


}

