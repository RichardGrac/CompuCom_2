package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.entity.ShoppingCart;
import com.example.CompuCom2.model.ProductCategoryModel;
import com.example.CompuCom2.model.ShoppingCartModel;
import com.example.CompuCom2.service.ProductCategoryService;
import com.example.CompuCom2.service.impl.ProductServiceImpl;
import com.example.CompuCom2.service.impl.ShoppingCartServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/shopping_cart")
public class SaleController {
    private static final Log LOG = LogFactory.getLog(SaleController.class);

    @Autowired
    @Qualifier("shoppingCartServiceImpl")
    private ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductServiceImpl productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/showcart")
    public ModelAndView showTheShoppingCart(Integer id_user){
        ModelAndView mav = new ModelAndView(Constants.SHOPPING_CART);
        ArrayList<ShoppingCartModel> shopping_cart = shoppingCartService.findAllProductsByUser(id_user);
        mav.addObject("shopping_cart", shopping_cart);
        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        mav.addObject("categories", productCategoryModels);
        return mav;
    }

    @RequestMapping("/addtoshoppingcart")
    public ModelAndView addProductToShoppingCart(Integer id_user, Integer id_prod){
        LOG.info("METHOD: addProductToShoppingCart() --PARAM: id_user="+id_user+", id_prod="+id_prod);
        ModelAndView mav = new ModelAndView("redirect:/shopping_cart/showcart?id_user=" + id_user);
        boolean result = shoppingCartService.addProductToSC(id_user, id_prod);
        if(result){
            mav.addObject("result", 1);
        }else{
            mav.addObject("result", 0);
        }
        return mav;
    }

    @RequestMapping("/deletefromshoppingcart")
    public ModelAndView deleteFromShoppingCart(Integer id_user, Integer id_prod){
        LOG.info("METHOD: deleteFromShoppingCart() --PARAM: id_user="+id_user+", id_prod="+id_prod);
        ModelAndView mav = new ModelAndView("redirect:/shopping_cart/showcart?id_user=" + id_user);
        int deleted = shoppingCartService.removeProductFromSC(id_user, id_prod);
        System.out.println("deleted="+deleted);
        mav.addObject("deleted", deleted);
        return mav;
    }

    @ResponseBody
    @GetMapping("/getnumberofproducts")
    public int getNumberOfProducts(Integer id_user){
        LOG.info("METHOD: getNumberOfProducts() --PARAM: id_user="+id_user);
        return shoppingCartService.numberOfProducts(id_user);
    }

    @GetMapping("/updatequantity")
    public ModelAndView updateQuantity(int sc_id, int quantity){
        LOG.info("METHOD: updateQuantity() --PARAM: id_sc="+sc_id+", quantity="+quantity);
        ShoppingCart sc = shoppingCartService.modifyQuantity(sc_id, quantity);
        return new ModelAndView("redirect:/shopping_cart/showcart?id_user=" + sc.getIdUser());
    }
}
