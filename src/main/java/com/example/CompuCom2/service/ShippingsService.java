package com.example.CompuCom2.service;

import com.example.CompuCom2.model.BillModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface ShippingsService {

    ArrayList<BillModel> getBillsByShippingStatus(String status);
}
