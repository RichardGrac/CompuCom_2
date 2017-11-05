package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.ProductQuantity;
import com.example.CompuCom2.model.ProductQuantityModel;
import org.springframework.stereotype.Component;

@Component("productQuantityConverter")
public class ProductQuantityConverter {
    public ProductQuantity modelToEntity(ProductQuantityModel productQuantityModel){
        ProductQuantity productQuantity = new ProductQuantity();
        productQuantity.setId(productQuantityModel.getId());
        productQuantity.setQuantity(productQuantityModel.getQuantity());
        return productQuantity;
    }

    public ProductQuantityModel entityToModel(ProductQuantity productQuantity){
        ProductQuantityModel productQuantityModel = new ProductQuantityModel();
        productQuantityModel.setId(productQuantity.getId());
        productQuantityModel.setQuantity(productQuantity.getQuantity());
        return productQuantityModel;
    }
}
