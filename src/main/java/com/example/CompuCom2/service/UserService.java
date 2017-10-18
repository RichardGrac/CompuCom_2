package com.example.CompuCom2.service;


import com.example.CompuCom2.model.UserAddressModel;
import com.example.CompuCom2.model.UserModel;

import java.util.List;

public interface UserService {
    public abstract UserModel addUser(UserModel userModel);
    public abstract List<UserModel> listAllUsers();
//    public abstract void removeUser(int id);
    public abstract UserModel findUserByIdModel(int id);
    public abstract UserAddressModel findUserAddressByIdModel(int id);
}
