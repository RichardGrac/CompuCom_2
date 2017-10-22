package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.Product;
import com.example.CompuCom2.model.ProductModel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service    // Por qué un Service?
public class ProductConverter {

    public Product modelToEntity(ProductModel productModel){
        Product product = new Product();
        product.setId(productModel.getId());
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        product.setCategory(productModel.getCategory());
        product.setPrice(productModel.getPrice());
        product.setImage(StringUtils.cleanPath(productModel.getImage().getOriginalFilename()));
        return product;
    }

    public ProductModel entityToModel(Product product){
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setDescription(product.getDescription());
        productModel.setCategory(product.getCategory());
        productModel.setPrice(product.getPrice());
        return productModel;
    }
}
