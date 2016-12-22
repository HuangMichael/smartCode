package com.bill.repository.user;

import com.bill.domain.user.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangbin on 16-12-10.
 * 用户信息数据库访问接口
 */

@Repository

public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {


    /**
     * 查询所有的用户信息
     */

    List<User> findAll();


    /**
     * @param id
     */

    User findById(Long id);

    /**
     * @param id 根据id删除
     */

    void delete(Long id);


    /**
     * @return 查询所有的id集合
     */
    @Query("SELECT p.id from  User p order by p.id ")
    List<Long> findAllId();
}
