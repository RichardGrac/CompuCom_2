package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.SalesProductView;
import com.example.CompuCom2.model.SalesProductViewModel;
import com.example.CompuCom2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesProductViewConverter {


    private final ProductRepository productRepository;

    @Autowired
    SalesProductViewConverter (ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public SalesProductViewModel entityToModel(SalesProductView salesProductView){
        return SalesProductViewModel.builder()
                .product(productRepository.findById(salesProductView.getId_product()))
                .quantity(salesProductView.getQuantity())
                .build();
    }
}
