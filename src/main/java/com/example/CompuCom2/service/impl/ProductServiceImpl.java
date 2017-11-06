package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.converter.DiscountConverter;
import com.example.CompuCom2.converter.ProductConverter;
import com.example.CompuCom2.entity.Product;
import com.example.CompuCom2.model.DiscountModel;
import com.example.CompuCom2.model.ProductModel;
import com.example.CompuCom2.repository.DiscountRepository;
import com.example.CompuCom2.repository.ProductQuantityRepository;
import com.example.CompuCom2.repository.ProductRepository;
import com.example.CompuCom2.service.ProductService;
import com.example.CompuCom2.utils.storage.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    public static final Log LOG = LogFactory.getLog(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    @Qualifier("discountRepository")
    private DiscountRepository discountRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private DiscountConverter discountConverter;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductQuantityServiceImpl productQuantityServiceImpl;

    @Override
    public ProductModel saveProduct(ProductModel productModel) {
        LOG.info("METHOD: saveProduct() --PARAMS: productModel=" + productModel);
        ProductModel aux = productConverter.entityToModel(productRepository.save(productConverter.modelToEntity(productModel)));
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
        LOG.info("METHOD: getAllProducts()");
        List<ProductModel> productModels = new ArrayList<>();

        for (Product product : productRepository.findAll()){
            // Para cada Producto seteamos su Descuento
            ProductModel productModel = productConverter.entityToModel(product);
            productModel.setDiscount(getDiscountById(productModel.getId()));
            productModel.setProductQuantityModel(productQuantityServiceImpl.getQuantityById(productModel.getId()));
            productModels.add(productModel);
        }
        return productModels;
    }

    @Override
    public List<ProductModel> getAllProductsByCategory(String category) {
        LOG.info("METHOD: getAllProductsByCategory() --PARAMS: category=" + category);
        List<ProductModel> productModels = new ArrayList<>();

        for(Product product : productRepository.findAllByCategory(category)){
            // Para cada Producto seteamos su Descuento
            ProductModel productModel = productConverter.entityToModel(product);
            productModel.setDiscount(getDiscountById(productModel.getId()));
            productModel.setProductQuantityModel(productQuantityServiceImpl.getQuantityById(productModel.getId()));
            productModels.add(productModel);
        }
        return productModels;
    }

    @Override
    public List<ProductModel> getAllProductsBySearch(String search) {
        LOG.info("METHOD: getAllProductsBySearch() --PARAMS: search=" + search);
        List<ProductModel> productModels = new ArrayList<>();

        for(Product product : productRepository.getAllProductsBySearch(search)){
            // Para cada Producto seteamos su Descuento
            ProductModel productModel = productConverter.entityToModel(product);
            productModel.setDiscount(getDiscountById(productModel.getId()));
            productModel.setProductQuantityModel(productQuantityServiceImpl.getQuantityById(productModel.getId()));
            productModels.add(productModel);
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

    @Override
    public DiscountModel getDiscountById(int id) {
        return discountConverter.entityToModel(discountRepository.findById(id));
    }

    @Override
    public DiscountModel saveDiscount(DiscountModel discountModel) {
        LOG.info("METHOD: saveDiscount() --PARAMS: discountModel=" + discountModel);
        return discountConverter.entityToModel(discountRepository.save(discountConverter.modelToEntity(discountModel)));
    }


}
