package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SaleController {
    private static final Log LOG = LogFactory.getLog(SaleController.class);

    @RequestMapping("/shopping_cart")
    public ModelAndView test(){
        ModelAndView mav = new ModelAndView(Constants.SHOPPING_CART);
        return mav;
    }

}
