package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.DealsBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface DealsBannerRepository extends JpaRepository<DealsBanner , Serializable> {

    DealsBanner findById(Integer id);
    @Transactional
    void deleteById(Integer id);

    @Query("select d.image from DealsBanner d where d.id = :id")
    String findImageById(@Param("id") Integer id);

    @Query("select d from DealsBanner d where active = true ")
    List<DealsBanner> findActives();
}
