package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.ProductCategory;
import com.example.CompuCom2.model.ProductCategoryModel;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryConverter {

    public ProductCategory modelToEntity(ProductCategoryModel productCategoryModel){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(productCategoryModel.getId());
        productCategory.setName(productCategoryModel.getName());
        return productCategory;
    }

    public ProductCategoryModel entityToModel(ProductCategory productCategory){
        ProductCategoryModel productCategoryModel = new ProductCategoryModel();
        productCategoryModel.setId(productCategory.getId());
        productCategoryModel.setName(productCategory.getName());
        return productCategoryModel;
    }
}
