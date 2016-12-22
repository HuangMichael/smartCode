package com.bill.service.user;

import com.bill.domain.user.User;
import com.bill.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin on 16-12-10.
 */
@Service
@CacheConfig(cacheNames = "usersCache")
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * @return 查询所有的用户信息
     */
    @Cacheable(value = "users", key = "'users'")
    public List<User> findAll() {
        return userRepository.findAll();
    }


    /**
     * @param id
     * @return 根据id查询
     */
    @Cacheable(value = "user", key = "'user_'+#p0")
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * @param user 保存用户信息
     */
    @CacheEvict(value = {"users"}, key = "'user_'+#user.id", allEntries = true)
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * @param id 删除用户信息
     */
    @CacheEvict(value = {"users"}, key = "'user_'+#p0", allEntries = true)
    public void delete(Long id) {
        userRepository.delete(id);
    }

}
