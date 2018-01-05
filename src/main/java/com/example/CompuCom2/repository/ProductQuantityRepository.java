package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("productQuantityRepository")
public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Serializable> {

    @Query("select pq from ProductQuantity as pq where pq.id = :id")
    ProductQuantity findById(int id);
    ProductQuantity save(ProductQuantity productQuantity);
}
