package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userAddressRepository")
public interface UserAddressRepository  extends JpaRepository<UserAddress, Serializable> {

    @Query("select ua from UserAddress as ua where ua.id = :id")
    UserAddress findById(@Param("id") int id);
}
