package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.Bill;
import com.example.CompuCom2.entity.Details;
import com.example.CompuCom2.model.BillModel;
import com.example.CompuCom2.model.DetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("billConverter")
public class BillConverter {

    @Autowired
    @Qualifier("detailsConverter")
    private DetailsConverter detailsConverter;

    @Autowired
    @Qualifier("shippingConverter")
    private ShippingConverter shippingConverter;

    public BillModel entityToModel(Bill bill){
        BillModel billModel = new BillModel();
        billModel.setId(bill.getId());
        billModel.setShipping_price(bill.getShipping_price());
        billModel.setIva(bill.getIva());
        billModel.setSubtotal(bill.getSubtotal());
        billModel.setTotal(bill.getTotal());
        billModel.setDate(bill.getDate());

        List<DetailsModel> detailsModels = new ArrayList<>();
        for (Details detail : bill.getDetails_bill()) {
            detailsModels.add(detailsConverter.entityToModel(detail));
        }
        billModel.setDetails_bill(detailsModels);
        billModel.setShipping(shippingConverter.entityToModel(bill.getShipping()));
        billModel.setId_user(bill.getUserPropietary_id());
        return billModel;
    }

    public Bill modelToEntity(BillModel billModel){
        Bill bill = new Bill();
        bill.setId(billModel.getId());
        bill.setShipping_price(billModel.getShipping_price());
        bill.setIva(billModel.getIva());
        bill.setSubtotal(billModel.getSubtotal());
        bill.setTotal(billModel.getTotal());
        bill.setDate(billModel.getDate());

        List<Details> details = new ArrayList<>();
        for (DetailsModel detailModel : billModel.getDetails_bill()) {
            details.add(detailsConverter.modelToEntity(detailModel));
        }
        bill.setDetails_bill(details);
        bill.setShipping(shippingConverter.modelToEntity(billModel.getShipping()));
        bill.setUserPropietary_id(billModel.getId_user());
        return bill;
    }
}
