package com.example.CompuCom2.model;

import com.example.CompuCom2.entity.Role;
import com.example.CompuCom2.entity.UserAddress;

import java.util.Set;

public class UserModel {
    private int id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
    private UserAddress userAdress;
    private ShoppingCartModel shoppingCartModel;


    public UserModel(){
        userAdress = new UserAddress();
    }

    public UserModel(int id, String username, String password, String email, String role, UserAddress userAdress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserAddress getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(UserAddress userAdress) {
        this.userAdress = userAdress;
    }

    public ShoppingCartModel getShoppingCartModel() {
        return shoppingCartModel;
    }

    public void setShoppingCartModel(ShoppingCartModel shoppingCartModel) {
        this.shoppingCartModel = shoppingCartModel;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userAdress=" + userAdress +
                '}';
    }
}
