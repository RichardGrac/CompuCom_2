package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {
    public abstract User findById(int id);
}
