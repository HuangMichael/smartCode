package com.bill.repository.person;

import com.bill.domain.person.Person;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangbin on 16-12-10.
 * 数据库访问接口
 */

@Repository
@CacheConfig(cacheNames = "personsCache")
public interface PersonRepository extends CrudRepository<Person,Long>, PagingAndSortingRepository<Person,Long> {
    /**
     * @return 查询所有的id集合
     */
    @Cacheable(value = "personIdList", key = "'personIdList'")
    @Query("SELECT p.id from  Person p")
    List<Long> findAllId();

    /**
     * @param id id
     * @return 根据id查询人员信息
     */
    Person findById(Long id);

}