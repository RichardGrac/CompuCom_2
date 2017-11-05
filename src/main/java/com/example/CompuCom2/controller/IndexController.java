package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
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

    @RequestMapping("/about")
    public ModelAndView about(){
        ModelAndView mav = new ModelAndView(Constants.ABOUT);
        return mav;
    }

    @RequestMapping("/questions")
    public ModelAndView questions(){
        ModelAndView mav = new ModelAndView(Constants.QUESTIONS);
        return mav;
    }
}
