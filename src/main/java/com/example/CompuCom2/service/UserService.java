package com.example.CompuCom2.service;


import com.example.CompuCom2.entity.User;
import com.example.CompuCom2.model.UserModel;

public interface UserService {
//    public abstract UserModel addUser(UserModel userModel);
//    public abstract List<UserModel> listAllUsers();
    public abstract User findUserById(int id);
//    public abstract void removeUser(int id);
    public abstract UserModel findUserByIdModel(int id);
}
