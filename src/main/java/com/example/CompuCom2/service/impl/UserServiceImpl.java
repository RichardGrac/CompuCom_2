package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.converter.UserAddressConverter;
import com.example.CompuCom2.converter.UserConverter;
import com.example.CompuCom2.entity.User;
import com.example.CompuCom2.entity.UserAddress;
import com.example.CompuCom2.model.UserAddressModel;
import com.example.CompuCom2.model.UserModel;
import com.example.CompuCom2.repository.UserAddressRepository;
import com.example.CompuCom2.repository.UserRepository;
import com.example.CompuCom2.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private static final Log LOG = LogFactory.getLog(UserService.class);

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("userConverter")
    private UserConverter userConverter;

    @Autowired
    @Qualifier("userAddressRepository")
    private UserAddressRepository userAddressRepository;

    @Autowired
    @Qualifier("userAddressConverter")
    private UserAddressConverter userAddressConverter;

    @Override
    public UserModel addUser(UserModel userModel) {
        LOG.info("METHOD: addUser() --PARAMS: " + userModel.toString());
        User user = userRepository.save(userConverter.convertUserModel2User(userModel));
        // Retornamos en forma de "Modelo" para el controlador
        return userConverter.convertUser2UserModel(user);
    }

    @Override
    public List<UserModel> listAllUsers() {
        LOG.info("METHOD: listAllUsers() --PARAMS: Nothing");
        List<UserModel> usersModel = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user: users) {
            usersModel.add(userConverter.convertUser2UserModel(user));
        }
        return usersModel;
    }

    @Override
    public UserModel findUserByIdModel(int id) {
        LOG.info("METHOD: findUserByModel() --PARAMS: id=" + id);
        return userConverter.convertUser2UserModel(userRepository.findById(id));
    }

    @Override
    public UserAddressModel addAddressUser(UserAddressModel userAddressModel) {
        LOG.info("METHOD: addUser() --PARAMS: " + userAddressModel.toString());
        UserAddress userAddress = userAddressRepository.save(userAddressConverter.convertUserAddressModel2UserAddress(userAddressModel));
        // Retornamos en forma de "Modelo" para el controlador
        return userAddressConverter.convertUserAddress2UserAddressModel(userAddress);
    }

    @Override
    public UserAddressModel findUserAddressByIdModel(int id) {
        LOG.info("METHOD: findUserByModel() --PARAMS: id=" + id);
        return userAddressConverter.convertUserAddress2UserAddressModel(userAddressRepository.findById(id));
    }
}
