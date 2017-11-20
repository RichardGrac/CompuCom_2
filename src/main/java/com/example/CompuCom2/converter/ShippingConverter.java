package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.Shipping;
import com.example.CompuCom2.model.ShippingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("shippingConverter")
public class ShippingConverter {

    @Autowired
    @Qualifier("statusShippingConverter")
    private StatusShippingConverter statusConverter;

    public ShippingModel entityToModel(Shipping shipping){
        ShippingModel shippingModel = new ShippingModel();
        shippingModel.setId(shipping.getId());
        shippingModel.setStreet(shipping.getStreet());
        shippingModel.setNumber(shipping.getNumber());
        shippingModel.setColony(shipping.getColony());
        shippingModel.setCity(shipping.getCity());
        shippingModel.setState(shipping.getState());
        shippingModel.setZip(shipping.getZip());
        shippingModel.setCountry(shipping.getCountry());
        shippingModel.setReference(shipping.getReference());
        shippingModel.setStatusShippingModel(statusConverter.entityToModel(shipping.getStatus_shipping()));
        return shippingModel;
    }

    public Shipping modelToEntity(ShippingModel shippingModel){
        Shipping shipping = new Shipping();
        shipping.setId(shippingModel.getId());
        shipping.setStreet(shippingModel.getStreet());
        shipping.setNumber(shippingModel.getNumber());
        shipping.setColony(shippingModel.getColony());
        shipping.setCity(shippingModel.getCity());
        shipping.setState(shippingModel.getState());
        shipping.setZip(shippingModel.getZip());
        shipping.setCountry(shippingModel.getCountry());
        shipping.setReference(shippingModel.getReference());
        shipping.setStatus_shipping(statusConverter.modelToEntity(shippingModel.getStatusShippingModel()));
        return shipping;
    }

}
