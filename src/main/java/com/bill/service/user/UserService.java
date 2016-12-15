package com.bill.service.user;

import com.bill.domain.user.User;
import com.bill.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huangbin on 16-12-10.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * @return 查询所有的用户信息
     */
    public Iterable findAll() {
        return userRepository.findAll();
    }


    /**
     * @param userName 用户名
     * @return 根据用户名称查询
     */
    public Iterable<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }




    public void save(User user){


        userRepository.save(user);
    }


}
