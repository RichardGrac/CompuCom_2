package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.StatusShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("statusShippingRepository")
public interface StatusShippingRepository extends JpaRepository<StatusShipping, Serializable> {

    StatusShipping findById(int id_ss);
}
