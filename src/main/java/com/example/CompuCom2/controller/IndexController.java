package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String goIndex(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView(Constants.INDEX);
        return mav;
    }
}
