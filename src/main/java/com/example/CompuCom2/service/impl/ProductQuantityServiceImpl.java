package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.converter.ProductQuantityConverter;
import com.example.CompuCom2.model.ProductQuantityModel;
import com.example.CompuCom2.repository.ProductQuantityRepository;
import com.example.CompuCom2.service.ProductQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductQuantityServiceImpl implements ProductQuantityService{

    @Autowired
    @Qualifier("productQuantityRepository")
    private ProductQuantityRepository productQuantityRepository;

    @Autowired
    @Qualifier("productQuantityConverter")
    private ProductQuantityConverter productQuantityConverter;

    @Override
    public ProductQuantityModel getQuantityById(int id) {
        return productQuantityConverter.entityToModel(productQuantityRepository.findById(id));
    }

    @Override
    public ProductQuantityModel saveQuantity(ProductQuantityModel productQuantityModel) {
        return productQuantityConverter.entityToModel(productQuantityRepository.save(productQuantityConverter.modelToEntity(productQuantityModel)));
    }
}
