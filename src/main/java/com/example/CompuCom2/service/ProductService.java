package com.example.CompuCom2.service;

import com.example.CompuCom2.model.DiscountModel;
import com.example.CompuCom2.model.ProductModel;

import java.util.List;

public interface ProductService {
    ProductModel saveProduct(ProductModel productModel);
    ProductModel getProductById(Integer id);
    List<ProductModel> getAllProducts();
    void deleteProductById(Integer id);
    String findImageById(Integer id);

    DiscountModel getDiscountById(int id);
    DiscountModel saveDiscount(DiscountModel discountModel);
}
