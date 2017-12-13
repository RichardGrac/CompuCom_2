package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {

    @Query("select u from User as u where u.id = :id")
    User findById(@Param("id") int id);

    @Query("select u from User as u where u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("select u from User as u where u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("select t.email from User as t")
    List<String> getEmails();

}
