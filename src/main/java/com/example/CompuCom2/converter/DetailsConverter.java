package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.Details;
import com.example.CompuCom2.model.DetailsModel;
import org.springframework.stereotype.Component;

@Component("detailsConverter")
public class DetailsConverter {

    public DetailsModel entityToModel(Details details){
        DetailsModel detailsModel = new DetailsModel();
        detailsModel.setId(details.getId());
        detailsModel.setId_prod(details.getId_prod());
        detailsModel.setName(details.getName());
        detailsModel.setPrice(details.getPrice());
        detailsModel.setDiscount(details.getDiscount());
        detailsModel.setQuantity(details.getQuantity());
        return detailsModel;
    }

    public Details modelToEntity(DetailsModel detailsModel){
        Details details = new Details();
        details.setId(detailsModel.getId());
        details.setId_prod(detailsModel.getId_prod());
        details.setName(detailsModel.getName());
        details.setPrice(detailsModel.getPrice());
        details.setDiscount(detailsModel.getDiscount());
        details.setQuantity(detailsModel.getQuantity());
        return details;
    }
}
