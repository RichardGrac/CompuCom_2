package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.converter.BillConverter;
import com.example.CompuCom2.entity.Bill;
import com.example.CompuCom2.model.BillModel;
import com.example.CompuCom2.repository.BillRepository;
import com.example.CompuCom2.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("billServiceImpl")
public class BillServiceImpl implements BillService {

    @Autowired
    @Qualifier("billRepository")
    private BillRepository billRepository;

    @Autowired
    @Qualifier("billConverter")
    private BillConverter billConverter;

    @Override
    public ArrayList<BillModel> getAllBills() {
        ArrayList<BillModel> billModels = new ArrayList<>();
        for (Bill bill : billRepository.findAll()) {
            BillModel billModel = billConverter.entityToModel(bill);
            billModels.add(billModel);
        }
        return billModels;
    }

    @Override
    public BillModel findBill(Integer bill_id) {
        Bill bill = billRepository.findById(bill_id);
        if (bill != null){
            return billConverter.entityToModel(bill);
        }else{
            return null;
        }
    }

//    @Override
//    public ArrayList<BillModel> getAllByUser(int id_user) {
//        ArrayList<BillModel> billModels = new ArrayList<>();
//        for (Bill bill : billRepository.findAllByBill_id(id_user)) {
//            BillModel billModel = billConverter.entityToModel(bill);
//            billModels.add(billModel);
//        }
//        return billModels;
//    }
}
