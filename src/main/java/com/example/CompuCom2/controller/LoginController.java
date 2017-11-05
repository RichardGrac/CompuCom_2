package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.UserModel;
import jdk.internal.org.objectweb.asm.util.TraceAnnotationVisitor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView showLoginForm(@RequestParam(name = "error", required = false) String error,
                                      @RequestParam(name = "logout", required = false) String logout){
        ModelAndView modelAndView = new ModelAndView(Constants.LOGIN);
        modelAndView.addObject("error", error);
        modelAndView.addObject("logout", logout);
        return modelAndView;
    }

    @GetMapping({"/loginsuccess", "/"})
    public ModelAndView loginCheck(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        UserModel userGlobal  = (UserModel) httpSession.getAttribute("userGlobal");
        if (userGlobal != null) {
            if (userGlobal.getRoles().toString().contains("ADMIN")) {
                modelAndView.setViewName("redirect:/admin/index");
            }
        }
        return modelAndView;
    }
}
