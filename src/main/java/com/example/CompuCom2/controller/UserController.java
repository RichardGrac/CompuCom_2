package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.converter.UserAddressConverter;
import com.example.CompuCom2.entity.Role;
import com.example.CompuCom2.model.UserAddressModel;
import com.example.CompuCom2.model.UserModel;
import com.example.CompuCom2.service.RoleService;
import com.example.CompuCom2.service.UserService;
import com.example.CompuCom2.service.impl.BillServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    @Autowired
    @Qualifier("billServiceImpl")
    private BillServiceImpl billService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/userform")
    private ModelAndView userForm(@RequestParam(name = "id", required = false) Integer id, Model model){
        LOG.info("METHOD: userForm() --PARAMS: id=" + id);
        ModelAndView modelAndView = new ModelAndView(Constants.USER_FORM);
        UserModel pop = (UserModel) model.asMap().get("user");

        UserModel userModel = new UserModel();
        UserAddressModel userAddressModel = new UserAddressModel();
        if (id != null && id != 0){
            userModel = userService.findUserByIdModel(id);
            userAddressModel = userService.findUserAddressByIdModel(id);
        }
        if (pop != null) {
            modelAndView.addObject("userModel", pop);
            modelAndView.addObject("userAddressModel", model.asMap().get("address"));
            modelAndView.addObject("repeat", true);
        }else {
            modelAndView.addObject("userModel", userModel);
            modelAndView.addObject("userAddressModel", userAddressModel);
        }

        return modelAndView;
    }

    @PostMapping("/adduser")
    //El name="userModel" se corresponde con el objeto en el HTML 'contactModel'
    // y el UserModel usermodel se corresponde con Java
    private ModelAndView addUser(@ModelAttribute(name="userModel") UserModel userModel,
                           @ModelAttribute(name = "userAddressModel") UserAddressModel userAddressModel,
                           RedirectAttributes redirectAttributes,
                           Model model){

        //Comprobacion de usuarios repetidos
        if (userService.findAllEmail().contains(userModel.getEmail())){
            redirectAttributes.addFlashAttribute("user", userModel);
            redirectAttributes.addFlashAttribute("address", userAddressModel);
            return new ModelAndView("redirect:/registro");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/users/showusers");
        LOG.info("METHOD: addUser() --PARAMS: " + userModel.toString() + " --userAddressModel: " + userAddressModel);
        // Seteamos el obj. UserAddress que tenemos dentro de userModel
        userModel.setUserAdress(userAddressConverter.modelToEntity(userAddressModel));
        //Lo añadimos, si es correcta la adición sacamos su ID para hacer referencia al Domicilio:
        if (userModel.getRoles() == null){
            Set<Role> roleSet = new HashSet<>();
            Role role = roleService.findByType("USER");
            roleSet.add(role);
            userModel.setRoles(roleSet);
            modelAndView.setViewName("redirect:/index");
        }
        UserModel userModel1 = userService.addUser(userModel);
        if (userModel1 != null){
            userAddressModel.setId(userModel1.getId());
            if (userService.addAddressUser(userAddressModel) == null){
//                model.addAttribute("result", 0);
                modelAndView.addObject("result",0);
            }else {
//                model.addAttribute("result", 1);
                modelAndView.addObject("result",1);
            }
        }else{
//            model.addAttribute("result", 0);
            modelAndView.addObject("result",0);
        }
        return modelAndView;
    }

    @GetMapping("/showusers")
    public ModelAndView showUsers(@RequestParam(name = "result", required = false) Integer result){
        LOG.info("METHOD: showUsers()");
        ModelAndView mav = new ModelAndView(Constants.USERS);
        // El for:each que se recorre en el HTML es "users"
        mav.addObject("users", userService.listAllUsers());
        mav.addObject("result",result);
        return mav;
    }

    @GetMapping("/removeUser")
    public ModelAndView removeUser(@RequestParam(name = "id", required = true) Integer id){
        LOG.info("METHOD: removeUser() --PARAMS: id=" + id);
        ModelAndView modelAndView = new ModelAndView("redirect:/users/showusers");
        userService.removeUser(id);
        modelAndView.addObject("delete", true);
        return modelAndView;
    }

    @GetMapping("/edit-user")
    public ModelAndView editUser(@RequestParam(name = "id") Integer id,
                                 HttpSession httpSession){
        UserModel userGlobal = (UserModel) httpSession.getAttribute("userGlobal");
        ModelAndView modelAndView = new ModelAndView(Constants.REGISTER);
        if (!id.equals(userGlobal.getId())){
            modelAndView.setViewName("redirect:/index");
            modelAndView.addObject("autherr", true);
            return modelAndView;
        }
        modelAndView.addObject("userModel", userService.findUserByIdModel(id));
        modelAndView.addObject("userAddressModel", userService.findUserAddressByIdModel(id));
        return modelAndView;
    }

    @PostMapping("/save-user")
    public ModelAndView saveUser(@ModelAttribute(name = "userModel") UserModel userModel,
                                 @ModelAttribute(name = "userAddressModel") UserAddressModel userAddressModel){
        LOG.info("METHOD: saveUser(), --PARAMS: " + userAddressModel.toString() + "," + userModel.toString());
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        userModel.setUserAdress(userAddressConverter.modelToEntity(userAddressModel));
        if (userService.addUser(userModel) != null){
            modelAndView.addObject("update", true);
        }else {
            modelAndView.addObject("update", false);
        }
        return modelAndView;
    }

    @RequestMapping("/searchuser")
    public ModelAndView showUser(@RequestParam(name = "id", required = false) Integer id){
        LOG.info("METHOD: showUser() --PARAM: id="+id);
        ModelAndView mav = new ModelAndView(Constants.SEARCHUSER);
        if (id != null){
            UserModel user = userService.findUserByIdModel(id);
            if(user != null){
                mav.addObject("address", userService.findUserAddressByIdModel(user.getId()));
                mav.addObject("user", user);
//                mav.addObject("bills", billService.getAllByUser(id));
            }else{
                mav.addObject("notFound", 1);
            }
        }else{
            mav.addObject("user", null);
        }
        mav.addObject("search", id);
        return mav;
    }
}
