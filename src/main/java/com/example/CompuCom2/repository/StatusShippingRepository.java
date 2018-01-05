package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.StatusShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("statusShippingRepository")
public interface StatusShippingRepository extends JpaRepository<StatusShipping, Serializable> {

    @Query("select ss from StatusShipping as ss where ss.id = :id_ss")
    StatusShipping findById(@Param("id_ss") int id_ss);
}
