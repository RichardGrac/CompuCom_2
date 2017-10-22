package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.ProductModel;
import com.example.CompuCom2.service.ProductService;
import com.example.CompuCom2.utils.storage.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {
    public static final Log LOG = LogFactory.getLog(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/productform")
    public String productForm(@RequestParam(name = "id", required = false) Integer id, Model model){
        LOG.info("METHOD: productForm() --PARAMS: id=" + id);
        ProductModel productModel = new ProductModel();
        if (id != 0){
            productModel = productService.getProductById(id);
        }
        model.addAttribute("product", productModel);
        return Constants.PRODUCT_FORM;
    }
}
