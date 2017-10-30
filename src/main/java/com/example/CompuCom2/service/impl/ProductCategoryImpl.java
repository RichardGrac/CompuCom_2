package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.converter.ProductCategoryConverter;
import com.example.CompuCom2.entity.ProductCategory;
import com.example.CompuCom2.model.ProductCategoryModel;
import com.example.CompuCom2.repository.ProductCategoryRepository;
import com.example.CompuCom2.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryImpl implements ProductCategoryService{

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductCategoryConverter productCategoryConverter;

    @Override
    public List<ProductCategoryModel> findAll() {
        List<ProductCategoryModel> productCategoryModels = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryRepository.findAll()){
            productCategoryModels.add(productCategoryConverter.entityToModel(productCategory));
        }
        return productCategoryModels;
    }

    @Override
    public ProductCategoryModel findOneById(Integer id) {
        return productCategoryConverter.entityToModel(productCategoryRepository.findOne(id));
    }

    @Override
    public ProductCategoryModel save(ProductCategoryModel productCategoryModel) {
        return productCategoryConverter.entityToModel(productCategoryRepository.save(productCategoryConverter.modelToEntity(productCategoryModel)));
    }

    @Override
    public void delete(Integer id) {
        productCategoryRepository.deleteById(id);
    }
}
