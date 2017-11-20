package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.StatusShipping;
import com.example.CompuCom2.model.StatusShippingModel;
import org.springframework.stereotype.Component;

@Component("statusShippingConverter")
public class StatusShippingConverter {

    public StatusShippingModel entityToModel(StatusShipping status){
        StatusShippingModel statusModel = new StatusShippingModel();
        statusModel.setId(status.getId());
        statusModel.setStatus(status.getStatus());
        statusModel.setLast_update(status.getLast_update());
        return statusModel;
    }

    public StatusShipping modelToEntity(StatusShippingModel statusModel){
        StatusShipping status = new StatusShipping();
        status.setId(statusModel.getId());
        status.setStatus(statusModel.getStatus());
        status.setLast_update(statusModel.getLast_update());
        return status;
    }
}
