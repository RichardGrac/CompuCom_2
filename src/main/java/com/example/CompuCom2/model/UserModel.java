package com.example.CompuCom2.model;

import com.example.CompuCom2.entity.UserAddress;

public class UserModel {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private UserAddress userAdress;

    public UserModel(){
        userAdress = new UserAddress();
    }

    public UserModel(int id, String username, String password, String email, String role, UserAddress userAdress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userAdress = userAdress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserAddress getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(UserAddress userAdress) {
        this.userAdress = userAdress;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", userAdress=" + userAdress +
                '}';
    }
}
