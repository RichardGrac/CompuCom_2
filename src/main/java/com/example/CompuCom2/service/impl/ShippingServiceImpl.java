package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.converter.BillConverter;
import com.example.CompuCom2.entity.Bill;
import com.example.CompuCom2.model.BillModel;
import com.example.CompuCom2.repository.BillRepository;
import com.example.CompuCom2.service.ShippingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("shippingServiceImpl")
public class ShippingServiceImpl implements ShippingsService {

    @Autowired
    @Qualifier("billRepository")
    private BillRepository billRepository;

    @Autowired
    @Qualifier("billConverter")
    private BillConverter billConverter;

    @Override
    public ArrayList<BillModel> getBillsByShippingStatus(String status) {
        ArrayList<BillModel> bills = new ArrayList<>();
        for (Bill bill : billRepository.getAllBillsByStatus(status)) {
            bills.add(billConverter.entityToModel(bill));
        }
        return bills;
    }
}
