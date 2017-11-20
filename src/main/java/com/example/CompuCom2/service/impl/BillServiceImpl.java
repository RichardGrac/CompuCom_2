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

//            int user_id = billRepository.getUserIdOfBillId(billModel.getId());
//            System.out.println("user_id="+user_id);
//            billModel.setId_user(user_id);
            billModels.add(billModel);
        }
        return billModels;
    }
}
