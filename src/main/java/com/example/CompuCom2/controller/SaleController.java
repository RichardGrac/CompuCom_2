package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.ShoppingCartModel;
import com.example.CompuCom2.service.impl.ProductServiceImpl;
import com.example.CompuCom2.service.impl.ShoppingCartServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/showcart")
    public ModelAndView showTheShoppingCart(Integer id_user){
        ModelAndView mav = new ModelAndView(Constants.SHOPPING_CART);
        ArrayList<ShoppingCartModel> shopping_cart = shoppingCartService.findAllProductsByUser(id_user);
        mav.addObject("shopping_cart", shopping_cart);
        return mav;
    }

    @RequestMapping("/addtoshoppingcart")
    public String addProductToShoppingCart(Integer id_user, Integer id_prod){
        LOG.info("METHOD: addProductToShoppingCart() --PARAM: id_user="+id_user+", id_prod="+id_prod);
        if(shoppingCartService.addProductToSC(id_user, id_prod)){
            System.out.println("Producto="+id_prod + " agregado a user="+id_user);
        }else{
            System.out.println("NO se agrego el Producto="+id_prod + " a user="+id_user);
        }
        return "redirect:/index";
    }

}
