package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);

    @GetMapping("")
    public ModelAndView login(){
        LOG.info("METHOD: login()");
        ModelAndView mav = new ModelAndView(Constants.LOGIN);
        return mav;
    }
}
