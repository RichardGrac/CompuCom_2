package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable> {
    Product findById(Integer id);

    @Transactional
    void deleteById(Integer id);

    @Query("SELECT r.image FROM Product r where r.id = :id")
    String findImageById(@Param("id") Integer id);

    List<Product> findAllByCategory(String category);

//    @Query("SELECT * FROM Product WHERE category like \"%:search%\" OR description like \"%raz%\" OR name like \"%raz%\"")
//    List<Product> getAllProductsBySearch(@Param("search") String search);
    List<Product> findAllByCategoryContaining(String search);
}
