package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userAddressRepository")
public interface UserAddressRepository  extends JpaRepository<UserAddress, Serializable> {
    public abstract UserAddress findById(int id);
}
