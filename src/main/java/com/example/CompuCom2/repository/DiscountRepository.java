package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("discountRepository")
public interface DiscountRepository extends JpaRepository<Discount, Serializable>{
    Discount findById(int id);
}
