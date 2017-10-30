package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    public static final Log LOG = LogFactory.getLog(ProductController.class);

    @GetMapping("")
    public ModelAndView main(){
        LOG.info("METHOD: main()");
        ModelAndView mav = new ModelAndView(Constants.CATEGORIES);
        return mav;
    }
}
