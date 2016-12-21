package com.bill.service.person;

import com.bill.domain.person.Person;
import com.bill.repository.person.PersonRepository;
import com.bill.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin on 16-12-10.
 * 人员业务类
 */
@Service
public class PersonService extends BaseService {
    @Autowired
    PersonRepository personRepository;


    /**
     * @param id 人员信息
     * @return 查询人员信息
     */
    public Person findById(Long id) {
        return personRepository.findOne(id);
    }


    /**
     * @param id 人员id
     */
    @CacheEvict(value = {"personList"}, allEntries = true, key = "'personList'")
    public void delete(Long id) {
        personRepository.delete(id);
    }


    /**
     * @param pageable
     * @return 分页查询人员信息
     */
    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }


    /**
     * @return 查询所有的用户信息
     */
    @Cacheable(value = {"personList"}, key = "'personList'")
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }
}
