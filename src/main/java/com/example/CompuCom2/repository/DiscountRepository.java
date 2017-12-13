package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("discountRepository")
public interface DiscountRepository extends JpaRepository<Discount, Serializable>{

    @Query("select d from Discount as d where d.id = :id")
    Discount findById(@Param("id") int id);
}
