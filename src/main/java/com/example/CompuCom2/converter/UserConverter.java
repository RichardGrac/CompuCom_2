package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.Bill;
import com.example.CompuCom2.entity.User;
import com.example.CompuCom2.model.BillModel;
import com.example.CompuCom2.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("userConverter")
public class UserConverter {

    @Autowired
    @Qualifier("billConverter")
    private BillConverter billConverter;

    public User modelToEntity(UserModel userModel){
        User user = new User();
        user.setId(userModel.getId());
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setEmail(userModel.getEmail());
        user.setRole(userModel.getRoles());
//        user.setUserAdress(userModel.getUserAdress());

        List<Bill> bills = new ArrayList<>();
        if (userModel.getBills() != null){
            for (BillModel billModel : userModel.getBills()) {
                Bill bill = billConverter.modelToEntity(billModel);
                bills.add(bill);
            }
        }
        user.setBills(bills);

        return user;
    }

    public UserModel entityToModel(User user){
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setEmail(user.getEmail());
        userModel.setRoles(user.getRole());
//        userModel.setUserAdress(user.getUserAdress());

        List<BillModel> billsModels = new ArrayList<>();
        if (user.getBills() != null){
            for (Bill bill : user.getBills()) {
                BillModel billModel = billConverter.entityToModel(bill);
                billsModels.add(billModel);
            }
        }
        userModel.setBills(billsModels);
        return userModel;
    }
}
