package com.dev.simple.service.impl;

import com.dev.simple.enums.ExceptionEnum;
import com.dev.simple.exception.BusinessException;
import com.dev.simple.mapper.UserMapper;
import com.dev.simple.pojo.User;
import com.dev.simple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaotian.huang
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User addUser(User record) {
        int num = userMapper.insertWithCallback(record);
        if (num == 0) {
            throw new BusinessException(ExceptionEnum.ADD_USER_EXCP);
        }
        return userMapper.selectByPrimaryKey(record.getId());
    }
}
