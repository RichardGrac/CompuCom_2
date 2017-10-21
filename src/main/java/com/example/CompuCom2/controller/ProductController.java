package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    public static final Log LOG = LogFactory.getLog(ProductController.class);

    @GetMapping("/productform")
    public String productForm(){
        return Constants.PRODUCT_FORM;
    }
}
