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

    @Query("select p from Product as p where p.id = :id")
    Product findById(@Param("id") Integer id);

    @Transactional
    @Query("delete from Product as p where p.id = :id")
    void deleteById(@Param("id") Integer id);

    @Query("SELECT r.image FROM Product r where r.id = :id")
    String findImageById(@Param("id") Integer id);

    @Query("select p from Product as p where p.category = :category")
    List<Product> findAllByCategory(@Param("category") String category);
//' OR p.description like '%search%' OR p.name like '%search%'
    @Query("SELECT p FROM Product p WHERE p.category like '%' || :search || '%' or p.description like '%' || :search || '%' or p.name like '%' || :search || '%'")
    List<Product> getAllProductsBySearch(@Param("search") String search);
//    List<Product> findAllByCategoryContainingOrDescriptionContainingOrNameContaining(String search);
}
