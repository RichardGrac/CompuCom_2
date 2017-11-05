package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RoleRepository extends JpaRepository<Role,Serializable> {
    Role findById(Integer id);
}
