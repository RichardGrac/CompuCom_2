package com.example.CompuCom2.service;

import com.example.CompuCom2.model.BillModel;

import java.util.ArrayList;

public interface BillService {
    ArrayList<BillModel> getAllBills();
    BillModel findBill(Integer bill_id);
}
