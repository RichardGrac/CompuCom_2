package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.User;
import com.example.CompuCom2.model.UserModel;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter {
    public User convertUserModel2User(UserModel userModel){
        User user = new User();
        user.setId(userModel.getId());
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setEmail(userModel.getEmail());
        user.setRole(userModel.getRole());
        return user;
    }

    public UserModel convertUser2UserModel(User user){
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setEmail(user.getEmail());
        userModel.setRole(user.getRole());
        return userModel;
    }
}
