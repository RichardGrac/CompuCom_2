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
    public ProductModel saveProduct(ProductModel productModel) {
        ProductModel aux = new ProductModel();
        aux = productConverter.entityToModel(productRepository.save(productConverter.modelToEntity(productModel)));
        storageService.store(productModel.getImage());
        return aux;
    }

    @Override
    public ProductModel getProductById(Integer id) {
        return productConverter.entityToModel(productRepository.findById(id));
    }

    @Override
    public ProductModel updateProduct(ProductModel productModel) {
        if (!productModel.getImage().isEmpty()){
            //se hizo una modificacion a la imagen
            storageService.deleteOne(productRepository.findImageById(productModel.getId()));
            return saveProduct(productModel);
        }else {
            //no se modifico la imagen
            Product updatedProduct = productRepository.findById(productModel.getId());
            updatedProduct.setCategory(productModel.getCategory());
            updatedProduct.setDescription(productModel.getDescription());
            updatedProduct.setName(productModel.getName());
            updatedProduct.setPrice(productModel.getPrice());
            return productConverter.entityToModel(productRepository.save(updatedProduct));
        }
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
        storageService.deleteOne(findImageById(id));
        productRepository.deleteById(id);
    }

    @Override
    public String findImageById(Integer id) {
        return productRepository.findImageById(id);
    }
}
