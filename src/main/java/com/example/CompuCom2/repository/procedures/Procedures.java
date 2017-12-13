package com.example.CompuCom2.repository.procedures;

import com.example.CompuCom2.entity.User;
import com.example.CompuCom2.entity.UserAddress;
import com.example.CompuCom2.model.UserAddressModel;
import com.example.CompuCom2.model.UserModel;

import java.util.List;

public interface Procedures {
    List<String> userEmails();
    List<User> getUsers();
    User getUser(Integer i);
    void addUser(UserModel userModel, UserAddressModel userAddressModel);
}
