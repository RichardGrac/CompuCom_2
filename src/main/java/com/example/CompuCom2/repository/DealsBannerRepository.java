package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.DealsBanner;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

public interface DealsBannerRepository extends JpaRepository<DealsBanner , Serializable> {
    DealsBanner findById(Integer id);
    @Transactional
    void deleteById(Integer id);
}
