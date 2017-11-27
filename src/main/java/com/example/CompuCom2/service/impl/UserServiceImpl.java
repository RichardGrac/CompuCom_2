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
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserModel addUser(UserModel userModel) {
        LOG.info("METHOD: addUser() --PARAMS: " + userModel.toString());
        User user = userConverter.modelToEntity(userModel);
        LOG.info("User Entity - " + user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1 = userRepository.save(user);
        //Seteamos manualmente la dirección del Usuario correspondiente:
        UserAddressModel address = userAddressConverter.entityToModel(userModel.getUserAdress());
        address.setId(user1.getId());
        addAddressUser(address);
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
        User user = userRepository.findById(id);
        if (user != null){
            return userConverter.entityToModel(user);
        }else{
            return null;
        }
    }

    @Override
    public boolean removeUser(int id) {
        LOG.info("METHOD: removeUser() --PARAMS: id=" + id);
        User user = userRepository.findById(id);
        if (user != null){
            userRepository.delete(user);
            return true;
        }else {
            return false;
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

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserModel findUserModelByUsername(String username) {
        return userConverter.entityToModel(userRepository.findByUsername(username));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<String> findAllEmail() {
        return userRepository.getEmails();
    }

    @Override
    public UserModel findUserModelByEmail(String email) {
        return userConverter.entityToModel(userRepository.findByEmail(email));
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        LOG.info("METHOD updateUser() --PARAMS " + userModel);
        User user = changeValuesUser(userModel);
        UserAddress userAddress = updateAddress(userModel);
        userAddressRepository.save(userAddress);
        return userConverter.entityToModel(userRepository.save(user));
    }

    @Override
    public UserModel updatePassword(Integer id, String password) {
        User user = userRepository.findById(id);
        user.setPassword(passwordEncoder.encode(password));
        return userConverter.entityToModel(userRepository.save(user));
    }

    private User changeValuesUser(UserModel userModel){
        User user = userRepository.findById(userModel.getId());
        user.setUsername(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        if (userModel.getRoles() != null) {
            user.setRole(userModel.getRoles());
        }
        return user;
    }

    private UserAddress updateAddress(UserModel userModel){
        UserAddress userAddress = userAddressRepository.findById(userModel.getId());
        userAddress.setStreet(userModel.getUserAdress().getStreet());
        userAddress.setNumber(userModel.getUserAdress().getNumber());
        userAddress.setColony(userModel.getUserAdress().getColony());
        userAddress.setZip(userModel.getUserAdress().getZip());
        userAddress.setState(userModel.getUserAdress().getState());
        userAddress.setCity(userModel.getUserAdress().getCity());
        userAddress.setCountry(userModel.getUserAdress().getCountry());
        userAddress.setReference(userModel.getUserAdress().getReference());
        return userAddress;
    }
}
