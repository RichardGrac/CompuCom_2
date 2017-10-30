package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.Discount;
import com.example.CompuCom2.model.DiscountModel;
import org.springframework.stereotype.Component;

@Component("discountConverter")
public class DiscountConverter {

    public Discount modelToEntity(DiscountModel discountModel){
        Discount discount = new Discount();
        discount.setId(discountModel.getId());
        discount.setPercentage(discountModel.getPercentage());
        return discount;
    }

    public DiscountModel entityToModel(Discount discount){
        DiscountModel discountModel = new DiscountModel();
        discountModel.setId(discount.getId());
        discountModel.setPercentage(discount.getPercentage());
        return discountModel;
    }
}
