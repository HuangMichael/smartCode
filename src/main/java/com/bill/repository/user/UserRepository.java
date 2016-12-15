package com.bill.repository.user;

import com.bill.domain.user.User;
import org.springframework.cache.annotation.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Created by huangbin on 16-12-10.
 * 用户信息数据库访问接口
 */

@Repository
@CacheConfig(cacheNames = "usersCache")
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    @Cacheable(value = "users")
    Iterable<User> findAll();


    /**
     * @param userName 根据用户名称查询
     * @return
     */
    @Cacheable(key = "#userName")
    Iterable<User> findByUserName(String userName);

    /**
     * @param user 保存用戶信息
     */
    @CachePut(value = "user")
    User save(User user);
}
