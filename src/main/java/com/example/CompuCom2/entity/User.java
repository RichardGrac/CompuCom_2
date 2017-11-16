package com.example.CompuCom2.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserAddress userAdress;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns =
            { @JoinColumn(name = "user_id") },
            inverseJoinColumns =
            { @JoinColumn(name = "role_id") })
    private Set<Role> role = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_bills", joinColumns =
        @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns =
        @JoinColumn(name = "bill_id", referencedColumnName = "id"))
    private List<Bill> bills;

    public User(){}

    public User(String username, String password, String email, UserAddress userAdress) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userAdress = userAdress;
    }

    public User(String username, String password, String email, UserAddress userAdress, Set<Role> role, ShoppingCart shoppingCart, List<Bill> bills) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userAdress = userAdress;
        this.role = role;
        this.shoppingCart = shoppingCart;
        this.bills = bills;
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

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public UserAddress getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(UserAddress userAdress) {
        this.userAdress = userAdress;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userAdress=" + userAdress +
                ", role=" + role +
                ", shoppingCart=" + shoppingCart +
                ", bills=" + bills +
                '}';
    }
}
