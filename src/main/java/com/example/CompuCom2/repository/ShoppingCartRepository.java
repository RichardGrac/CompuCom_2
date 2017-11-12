package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("shoppingCartRepository")
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Serializable>{
    List<ShoppingCart> findAllByIdUser(int id_user);
    ShoppingCart findByIdUserAndIdProd(int id_user, int id_prod);
}
