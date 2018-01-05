package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Serializable>{
    @Transactional
    @Query("delete from ProductCategory as pc where pc.id = :id")
    void deleteById(@Param("id") Integer id);
}
