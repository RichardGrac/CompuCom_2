package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RoleRepository extends JpaRepository<Role,Serializable> {

    @Query("select r from Role as r where r.id = :id")
    Role findById(@Param("id") Integer id);

    @Query("select r from Role as r where r.type = :type")
    Role findByType(@Param("type") String type);
}
