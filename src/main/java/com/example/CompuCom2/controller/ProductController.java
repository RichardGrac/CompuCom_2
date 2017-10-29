package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.ProductModel;
import com.example.CompuCom2.service.ProductService;
import com.example.CompuCom2.utils.storage.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        if (id != null && id != 0){
            productModel = productService.getProductById(id);
        }
        model.addAttribute("productModel", productModel);
        return Constants.PRODUCT_FORM;
    }

    @PostMapping("/addproduct")
    public String addProduct(@ModelAttribute(name = "productModel") ProductModel productModel, Model model){
        LOG.info("METHOD: addProduct() --PARAMS: id=" + productModel);
        ProductModel productModel1 = productService.saveProduct(productModel);
        if (productModel1 != null){
            model.addAttribute("result", 1);
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/products/productform";
    }

    @GetMapping("/showproducts")
    public ModelAndView showProducts(){
        LOG.info("METHOD: showProducts()");
        ModelAndView mav = new ModelAndView(Constants.PRODUCTS);
        mav.addObject("products", productService.getAllProducts());
        return mav;
    }

    @GetMapping("/files")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@RequestParam Integer id) {
        String filename = productService.findImageById(id);
        if (filename != null){
            Resource file = storageService.loadAsResource(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
        return null;
    }


    @GetMapping("/removeproduct")
    public String deleteProduct(@RequestParam(name = "id") Integer id){
        productService.deleteProductById(id);
        return "redirect:/products/showproducts";
    }
}
