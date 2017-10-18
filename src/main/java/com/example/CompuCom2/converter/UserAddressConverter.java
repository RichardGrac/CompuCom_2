package com.example.CompuCom2.converter;

import com.example.CompuCom2.model.UserAddressModel;
import org.springframework.stereotype.Component;
import com.example.CompuCom2.entity.UserAddress;

@Component("userAddressConverter")
public class UserAddressConverter {
    public UserAddress convertUserAddressModel2UserAddress(UserAddressModel userAddressModel){
        UserAddress userAddress = new UserAddress();
        userAddress.setId(userAddressModel.getId());
        userAddress.setStreet(userAddressModel.getStreet());
        userAddress.setNumber(userAddressModel.getNumber());
        userAddress.setColony(userAddressModel.getColony());
        userAddress.setCity(userAddressModel.getCity());
        userAddress.setState(userAddressModel.getState());
        userAddress.setCountry(userAddressModel.getCountry());
        userAddress.setReference(userAddressModel.getReference());
        userAddress.setZip(userAddressModel.getZip());
        return userAddress;
    }

    public UserAddressModel convertUserAddress2UserAddressModel(UserAddress userAddress){
        UserAddressModel userAddressModel = new UserAddressModel();
        userAddressModel.setId(userAddress.getId());
        userAddressModel.setStreet(userAddress.getStreet());
        userAddressModel.setNumber(userAddress.getNumber());
        userAddressModel.setColony(userAddress.getColony());
        userAddressModel.setCity(userAddress.getCity());
        userAddressModel.setState(userAddress.getState());
        userAddressModel.setCountry(userAddress.getCountry());
        userAddressModel.setReference(userAddress.getReference());
        userAddressModel.setZip(userAddress.getZip());
        return userAddressModel;
    }

}
