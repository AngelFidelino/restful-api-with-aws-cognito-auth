package org.alfr.service;

import org.alfr.dao.UserDao;
import org.alfr.dto.User;
import org.alfr.mapping.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return UserMapper.INSTANCE.getUsersFrom(userDao.findAll());
    }
}
