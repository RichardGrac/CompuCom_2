package com.example.CompuCom2.service;

import com.example.CompuCom2.model.ProductCategoryModel;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryModel> findAll();
    ProductCategoryModel findOneById(Integer id);
    ProductCategoryModel save(ProductCategoryModel productCategoryModel);
    void delete(Integer id);
}
