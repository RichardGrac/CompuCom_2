package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository("shoppingCartRepository")
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Serializable>{

    @Query("select sc from ShoppingCart as sc where sc.idUser = :id_user")
    List<ShoppingCart> findAllByIdUser(@Param("id_user") int id_user);

    @Query("select sc from ShoppingCart as sc where sc.idUser = :id_user and sc.idProd = :id_prod")
    ShoppingCart findByIdUserAndIdProd(@Param("id_user") int id_user, @Param("id_prod") int id_prod);

    @Transactional
    @Query("delete from ShoppingCart as sc where sc.idUser = :id_user and sc.idProd = :id_prod")
    int removeByIdUserAndIdProd(@Param("id_user") int id_user, @Param("id_prod") int id_prod);

    @Query("select sc from ShoppingCart as sc where sc.idSc = :id_sc")
    ShoppingCart findByIdSc(int id_sc);

    @Transactional
    @Query("delete from ShoppingCart as sc where sc.idUser = :id_user")
    int removeAllByIdUser(@Param("id_user") int id_user);
}
