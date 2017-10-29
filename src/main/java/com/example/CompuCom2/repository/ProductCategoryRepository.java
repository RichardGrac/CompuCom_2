package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Serializable>{
    @Transactional
    void deleteById(Integer id);
}
