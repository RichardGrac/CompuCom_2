package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.converter.DiscountConverter;
import com.example.CompuCom2.entity.Product;
import com.example.CompuCom2.model.DiscountModel;
import com.example.CompuCom2.model.ProductModel;
import com.example.CompuCom2.model.ProductQuantityModel;
import com.example.CompuCom2.service.ProductCategoryService;
import com.example.CompuCom2.service.ProductQuantityService;
import com.example.CompuCom2.service.ProductService;
import com.example.CompuCom2.utils.storage.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    public static final Log LOG = LogFactory.getLog(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductQuantityService productQuantityService;

    @Autowired
    @Qualifier("discountConverter")
    private DiscountConverter discountConverter;

    @GetMapping("/productform")
    public String productForm(@RequestParam(name = "id", required = false) Integer id, Model model){
        LOG.info("METHOD: productForm() --PARAMS: id=" + id);
        ProductModel productModel = new ProductModel();
        DiscountModel discountModel = new DiscountModel();
        ProductQuantityModel productQuantityModel = new ProductQuantityModel();

        if (id != null && id != 0){
            productModel = productService.getProductById(id);
            discountModel = productService.getDiscountById(id);
        }
        model.addAttribute("productModel", productModel);
        model.addAttribute("discountModel", discountModel);
        model.addAttribute("quantityModel", productQuantityModel);
        model.addAttribute("categories", productCategoryService.findAll());
        return Constants.PRODUCT_FORM;
    }

    @PostMapping("/addproduct")
    public ModelAndView addProduct(@ModelAttribute(name = "productModel") ProductModel productModel,
                             DiscountModel discountModel, ProductQuantityModel productQuantityModel,
                                   Model model){
        ModelAndView modelAndView = new ModelAndView("redirect:/products/showproducts");
        LOG.info("METHOD: addProduct() --PARAMS: productModel=" + productModel + " \n--discountModel: " + discountModel);
        productModel.setDiscount(discountModel);
        productModel.setProductQuantityModel(productQuantityModel);

        ProductModel productModel1;

        if(productModel.getId() != null){
            //como el producto es diferente de null, el producto se va a actualizar
            productModel1 = productService.updateProduct(productModel);
//            ------ No se tendría que setear el id a saveDiscount()? : .saveDiscount(new DiscountModel(productModel.getId(), productModel.getDiscount().getPercentage()))
            productService.saveDiscount(productModel.getDiscount());
            productQuantityService.saveQuantity(productModel.getProductQuantityModel());
            if (productModel1 != null){
                modelAndView.addObject("result", 1);
            }else{
                modelAndView.addObject("result", 0);
            }
        }else {
            //el producto es nuevo y se va a guardar
            productModel1 = productService.saveProduct(productModel);
            if (productModel1 != null){
                discountModel.setId(productModel1.getId());
                productQuantityModel.setId(productModel.getId());
                if (productService.saveDiscount(discountModel) == null || productQuantityService.saveQuantity(productQuantityModel) == null){
                    modelAndView.addObject("result", 0);
                }else{
                    modelAndView.addObject("result", 1);
                }
            }else{
                modelAndView.addObject("result", 0);
            }

        }
        return modelAndView;
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
