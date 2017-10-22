package com.example.CompuCom2.service;

import com.example.CompuCom2.model.ProductModel;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductModel productModel);
    ProductModel getProductById(Integer id);
    List<ProductModel> getAllProducts();
    void deleteProductById(Integer id);
    String findImageById(Integer id);
}
