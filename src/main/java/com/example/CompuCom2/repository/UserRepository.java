package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {
    User findById(int id);
    User findByUsername(String username);
    User findByEmail(String email);

    @Query("select t.email from User as t")
    List<String> getEmails();

}
