package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.example.CompuCom2.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);

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
