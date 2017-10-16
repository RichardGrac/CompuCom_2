package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.converter.UserConverter;
import com.example.CompuCom2.entity.User;
import com.example.CompuCom2.model.UserModel;
import com.example.CompuCom2.repository.UserRepository;
import com.example.CompuCom2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("userConverter")
    private UserConverter userConverter;


    @Override
    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserModel findUserByIdModel(int id) {
        return userConverter.convertUser2UserModel(findUserById(id));
    }
}
