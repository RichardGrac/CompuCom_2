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
        User user = userRepository.save(userConverter.modelToEntity(userModel));
        // Retornamos en forma de "Modelo" para el controlador
        return userConverter.entityToModel(user);
    }

    @Override
    public List<UserModel> listAllUsers() {
        LOG.info("METHOD: listAllUsers()");
        List<UserModel> usersModel = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user: users) {
            usersModel.add(userConverter.entityToModel(user));
        }
        return usersModel;
    }

    @Override
    public UserModel findUserByIdModel(int id) {
        LOG.info("METHOD: findUserByModel() --PARAMS: id=" + id);
        return userConverter.entityToModel(userRepository.findById(id));
    }

    @Override
    public void removeUser(int id) {
        LOG.info("METHOD: removeUser() --PARAMS: id=" + id);
        User user = userRepository.findById(id);
        if (user != null){
            userRepository.delete(user);
        }
    }

    @Override
    public UserAddressModel addAddressUser(UserAddressModel userAddressModel) {
        LOG.info("METHOD: addUser() --PARAMS: " + userAddressModel.toString());
        UserAddress userAddress = userAddressRepository.save(userAddressConverter.modelToEntity(userAddressModel));
        // Retornamos en forma de "Modelo" para el controlador
        return userAddressConverter.entityToModel(userAddress);
    }

    @Override
    public UserAddressModel findUserAddressByIdModel(int id) {
        LOG.info("METHOD: findUserAddressByIdModel() --PARAMS: id=" + id);
        UserAddress userAddress = userAddressRepository.findById(id);
        if (userAddress != null){
            return userAddressConverter.entityToModel(userAddressRepository.findById(id));
        }
        return new UserAddressModel();
    }

    @Override
    public void removeUserAddress(int id) {
        LOG.info("METHOD: removeUserAddress() --PARAMS: " + id);
        UserAddress userAddress = userAddressRepository.findById(id);
        if (userAddress != null){
            userAddressRepository.delete(userAddress);
        }
    }
}
