package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.converter.ProductConverter;
import com.example.CompuCom2.entity.Product;
import com.example.CompuCom2.model.ProductModel;
import com.example.CompuCom2.repository.ProductRepository;
import com.example.CompuCom2.service.ProductService;
import com.example.CompuCom2.utils.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private StorageService storageService;

    @Override
    public void saveProduct(ProductModel productModel) {
        productRepository.save(productConverter.modelToEntity(productModel));
        storageService.store(productModel.getImage());
    }

    @Override
    public ProductModel getProductById(Integer id) {
        return productConverter.entityToModel(productRepository.findById(id));
    }

    @Override
    public List<ProductModel> getAllProducts() {
        List<ProductModel> productModels = new ArrayList<>();
        for (Product producto : productRepository.findAll()){
            productModels.add(productConverter.entityToModel(producto));
        }
        return productModels;
    }

    @Override
    public void deleteProductById(Integer id) {
        storageService.deleteOne(findImagenById(id));
        productRepository.deleteById(id);
    }

    @Override
    public String findImagenById(Integer id) {
        return productRepository.findImageById(id);
    }
}
