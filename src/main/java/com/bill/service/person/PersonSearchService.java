package com.bill.service.person;


import com.bill.domain.person.Person;
import com.bill.domain.user.User;
import com.bill.repository.person.PersonRepository;
import com.bill.repository.user.UserRepository;
import com.bill.service.BaseService;
import com.bill.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by huangbin on 2016/3/24.
 * 人员查询业务类
 */
@Service
public class PersonSearchService extends BaseService implements SortedSearchable {

    @Autowired
    PersonRepository personRepository;


    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public Iterable<Person> findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return personRepository.findAll();
    }


    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public Page<Person> findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return personRepository.findAll(pageable);
    }

}

