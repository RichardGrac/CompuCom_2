package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.SalesProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface SaleProductViewRepository extends JpaRepository<SalesProductView, Serializable> {
}
