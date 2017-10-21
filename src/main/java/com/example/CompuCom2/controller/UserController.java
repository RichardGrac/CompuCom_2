package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.converter.UserAddressConverter;
import com.example.CompuCom2.model.UserAddressModel;
import com.example.CompuCom2.model.UserModel;
import com.example.CompuCom2.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Log LOG = LogFactory.getLog(UserController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("userAddressConverter")
    private UserAddressConverter userAddressConverter;

    @GetMapping("/userform")
    private String userForm(@RequestParam(name = "id", required = false) Integer id, Model model){
        LOG.info("METHOD: userForm() --PARAMS: id=" + id);
        UserModel userModel = new UserModel();
        UserAddressModel userAddressModel = new UserAddressModel();
        if (id != null && id != 0){
            userModel = userService.findUserByIdModel(id);
            userAddressModel = userService.findUserAddressByIdModel(id);
        }
        model.addAttribute("userModel", userModel);
        model.addAttribute("userAddressModel", userAddressModel);
        return Constants.USER_FORM;
    }

    @PostMapping("/addUser")
    //El name="userModel" se corresponde con el objeto en el HTML 'contactModel'
    // y el UserModel usermodel se corresponde con Java
    private String addUser(@ModelAttribute(name="userModel") UserModel userModel,
                           @ModelAttribute(name = "userAddressModel") UserAddressModel userAddressModel,
                           Model model){
        LOG.info("METHOD: addUser() --PARAMS: " + userModel.toString());
        // Seteamos el obj. UserAddress que tenemos dentro de userModel
        userModel.setUserAdress(userAddressConverter.convertUserAddressModel2UserAddress(userAddressModel));
        //Lo añadimos, si es correcta la adición sacamos su ID para hacer referencia al Domicilio:
        UserModel userModel1 = userService.addUser(userModel);
        if (userModel1 != null){
            userAddressModel.setId(userModel1.getId());
            if (userService.addAddressUser(userAddressModel) == null){
                model.addAttribute("result", 0);
            }else {
                model.addAttribute("result", 1);
            }
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/users/showUsers";
    }

    @GetMapping("/showUsers")
    public ModelAndView showUsers(){
        ModelAndView mav = new ModelAndView(Constants.USERS);
        // El for:each que se recorre en el HTML es "users"
        mav.addObject("users", userService.listAllUsers());
        return mav;
    }

    @GetMapping("/removeUser")
    public ModelAndView removeUser(@RequestParam(name = "id", required = true) int id){
        userService.removeUser(id);
        return showUsers();
    }
}
