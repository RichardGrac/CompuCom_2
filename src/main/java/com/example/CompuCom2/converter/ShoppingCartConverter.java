package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.ShoppingCart;
import com.example.CompuCom2.model.ShoppingCartModel;
import org.springframework.stereotype.Component;

@Component("shoppingCartConverter")
public class ShoppingCartConverter {
    public ShoppingCart modelToEntity(ShoppingCartModel shoppingCartModel){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setIdSc(shoppingCartModel.getIdSc());
        shoppingCart.setIdUser(shoppingCartModel.getIdUser());
        shoppingCart.setQuantity(shoppingCartModel.getQuantity());
        return shoppingCart;
    }

    public ShoppingCartModel entityToModel(ShoppingCart shoppingCart){
        ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
        shoppingCartModel.setIdSc(shoppingCart.getIdSc());
        shoppingCartModel.setIdUser(shoppingCart.getIdUser());
        shoppingCartModel.setQuantity(shoppingCart.getQuantity());
        return shoppingCartModel;
    }
}
