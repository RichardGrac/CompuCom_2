package com.example.CompuCom2.controller;

import com.example.CompuCom2.model.UserModel;
import com.example.CompuCom2.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class AdministrationController {
    private static final Log LOG = LogFactory.getLog(AdministrationController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("/hello")
    public String greeting(){
        System.out.println("greeting()");
        return "Hello";
    }

    @GetMapping("/userform")
    private String redirectUserForm(@RequestParam(name = "id", required = false) int id, Model model){
        System.out.println("Hello");
        UserModel userModel = new UserModel();
        if(id != 0){
            userModel = userService.findUserByIdModel(id);
        }
        model.addAttribute("userModel", userModel);
        return "userform";
    }

//    @PostMapping("addUser")
//    private String addUser(@ModelAttribute(name="userModel") UserModel userModel, Model model){
//        LOG.info("METHOD: addUser() --PARAMS: " + userModel.toString());
//    }
}
