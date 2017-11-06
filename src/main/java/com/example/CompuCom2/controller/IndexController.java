package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.ProductCategoryModel;
import com.example.CompuCom2.model.ProductModel;
import com.example.CompuCom2.service.ProductCategoryService;
import com.example.CompuCom2.service.impl.ProductServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
//        Seteamos categorias:
        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        String search = "";

//        Colocamos solo los que tienen descuento
        mav.addObject("categories", productCategoryModels);
        ArrayList<ProductModel> productModels = (ArrayList<ProductModel>) productService.getAllProducts();
        ArrayList<ProductModel> productsWithDiscount = new ArrayList<>();
        for (ProductModel productModel : productModels) {
            if (productModel.getDiscount().getPercentage() != 0.0){
                productsWithDiscount.add(productModel);
            }
        }
        mav.addObject("categories", productCategoryModels);
        mav.addObject("products", resize_description(productsWithDiscount));
        mav.addObject("search", search);
        return mav;
    }

    @RequestMapping("/about")
    public ModelAndView about(){
        LOG.info("METHOD: about()");
        ModelAndView mav = new ModelAndView(Constants.ABOUT);
        String search = "";
        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        mav.addObject("categories", productCategoryModels);
        mav.addObject("search", search);
        return mav;
    }

    @RequestMapping("/questions")
    public ModelAndView questions(){
        LOG.info("METHOD: questions()");
        ModelAndView mav = new ModelAndView(Constants.QUESTIONS);
        String search = "";
        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        mav.addObject("categories", productCategoryModels);
        mav.addObject("search", search);
        return mav;
    }

    @RequestMapping("/fproducts")
    public ModelAndView fproducts(@RequestParam String category){
        LOG.info("METHOD: fproducts() --PARAMS: category="+category);
        ModelAndView mav = new ModelAndView(Constants.FPRODUCTS);
        String search = "";
        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        mav.addObject("categories", productCategoryModels);

        ArrayList<ProductModel> productModels = (ArrayList<ProductModel>) productService.getAllProductsByCategory(category);
        mav.addObject("products", resize_description(productModels));
        mav.addObject("categorySelected", category);
        mav.addObject("search", search);
        return mav;
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "search", required = true) String search, Model model){
        LOG.info("METHOD: search() --PARAMS: search="+search);
        String search1 = "";
        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        model.addAttribute("categories", productCategoryModels);

        ArrayList<ProductModel> productModels = (ArrayList<ProductModel>) productService.getAllProductsBySearch(search);
        model.addAttribute("products", resize_description(productModels));
        model.addAttribute("categorySelected", (productModels.size() + " productos encontrados ('" + search + "')"));
        model.addAttribute("search", search1);
        return Constants.FPRODUCTS;
    }

    private ArrayList<ProductModel> resize_description(ArrayList<ProductModel> productModels){
        for (ProductModel product : productModels) {
            String descripcion = product.getDescription();
            if (descripcion.length() > Constants.DESCRIPTION_SIZE){
                descripcion = descripcion.substring(0,Constants.DESCRIPTION_SIZE) + "...";
                product.setDescription(descripcion);
            }
        }
        return productModels;
    }

}
