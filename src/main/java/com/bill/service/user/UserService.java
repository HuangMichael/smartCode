package com.bill.service.user;

import com.bill.domain.user.User;
import com.bill.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * @return 查询所有的用户信息
     */
    @Cacheable(value = "user", key = "'users'")
    public List<User> findAll() {
        return userRepository.findAll();
    }


    /**
     * @param id
     * @return 根据id查询
     */
    @CachePut(value = "user", key = "'id_'+#p0")
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    @CachePut(value = "user", key = "'id_'+#user.id")
    public void save(User user) {
        userRepository.save(user);
    }

    @CacheEvict(value = "user", key = "'id_'+#p0")
    public void delete(Long id) {
        userRepository.delete(id);
    }


}
