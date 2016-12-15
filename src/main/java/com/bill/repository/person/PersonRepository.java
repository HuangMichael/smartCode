package com.bill.repository.person;

import com.bill.domain.person.Person;
import org.springframework.cache.annotation.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

/**
* Created by huangbin on 16-12-10.
* 数据库访问接口
*/

@Repository
@CacheConfig(cacheNames = "personsCache")
public interface PersonRepository extends CrudRepository<Person, Long>, PagingAndSortingRepository<Person, Long> {


}