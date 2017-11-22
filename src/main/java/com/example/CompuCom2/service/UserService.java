package com.example.CompuCom2.service;


import com.example.CompuCom2.entity.User;
import com.example.CompuCom2.model.UserAddressModel;
import com.example.CompuCom2.model.UserModel;

import java.util.List;

public interface UserService {
    // Para los usuarios y su cuenta
    UserModel addUser(UserModel userModel);
    List<UserModel> listAllUsers();
    //    public abstract void removeUser(int id);
    UserModel findUserByIdModel(int id);
    boolean removeUser(int id);

    // Para la dirección de los usuarios
    UserAddressModel addAddressUser(UserAddressModel userAddressModel);
    UserAddressModel findUserAddressByIdModel(int id);
    void removeUserAddress(int id);

    User findUserByUsername(String username);
    User findUserByEmail(String email);
    UserModel findUserModelByUsername(String username);
    UserModel findUserModelByEmail(String email);
    List<String> findAllEmail();
    UserModel updateUser(UserModel userModel);
    UserModel updatePassword(Integer id, String password);


}
