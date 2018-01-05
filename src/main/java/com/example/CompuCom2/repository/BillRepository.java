package com.example.CompuCom2.repository;

import com.example.CompuCom2.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("billRepository")
public interface BillRepository extends JpaRepository<Bill, Serializable>{

    List<Bill> findAll();
    Bill findById(int bill_id);

    @Query("select b from Bill as b where b.shipping.status_shipping.status= :status")
    List<Bill> getAllBillsByStatus(@Param("status") String status);
}
