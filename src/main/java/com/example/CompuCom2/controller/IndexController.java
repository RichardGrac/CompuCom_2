package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.DiscountModel;
import com.example.CompuCom2.model.ProductCategoryModel;
import com.example.CompuCom2.model.ProductModel;
import com.example.CompuCom2.service.ProductCategoryService;
import com.example.CompuCom2.service.impl.ProductServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final Log LOG = LogFactory.getLog(IndexController.class);

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/")
    public String goIndex(){
        LOG.info("METHOD: goIndex()");
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        LOG.info("METHOD: index()");
        ModelAndView mav = new ModelAndView(Constants.INDEX);
        ArrayList<ProductModel> productModels = (ArrayList<ProductModel>) productService.getAllProducts();
        ArrayList<ProductModel> productsWithDiscount = new ArrayList<>();
        for (ProductModel productModel : productModels) {
            if (productModel.getDiscount().getPercentage() != 0.0){
                productsWithDiscount.add(productModel);
            }
        }
        mav.addObject("products", productsWithDiscount);
        return mav;
    }

    @RequestMapping("/about")
    public ModelAndView about(){
        LOG.info("METHOD: about()");
        ModelAndView mav = new ModelAndView(Constants.ABOUT);
        return mav;
    }

    @RequestMapping("/questions")
    public ModelAndView questions(){
        LOG.info("METHOD: questions()");
        ModelAndView mav = new ModelAndView(Constants.QUESTIONS);
        return mav;
    }

    @RequestMapping("/fproducts")
    public ModelAndView fproducts(){
        LOG.info("METHOD: fproducts()");
        ModelAndView mav = new ModelAndView(Constants.FPRODUCTS);
        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        for (ProductCategoryModel product : productCategoryModels) {
        }
        mav.addObject("categories", productCategoryModels);
        return mav;
    }

}
