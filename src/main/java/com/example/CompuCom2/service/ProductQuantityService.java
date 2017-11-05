package com.example.CompuCom2.service;

import com.example.CompuCom2.model.ProductQuantityModel;

public interface ProductQuantityService {
    ProductQuantityModel getQuantityById(int id);
    ProductQuantityModel saveQuantity(ProductQuantityModel productQuantityModel);
}
