package com.example.CompuCom2.model;

import com.example.CompuCom2.entity.Role;
import com.example.CompuCom2.entity.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Builder
public class UserModel {
    private int id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
    private UserAddress userAdress;
    private ShoppingCartModel shoppingCartModel = null;
    private List<BillModel> bills = null;


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

    public List<BillModel> getBills() {
        return bills;
    }

    public void setBills(List<BillModel> bills) {
        this.bills = bills;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", userAdress=" + userAdress +
                ", shoppingCartModel=" + shoppingCartModel +
                ", bills=" + bills +
                '}';
    }
}
