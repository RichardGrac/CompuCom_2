package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.converter.UserAddressConverter;
import com.example.CompuCom2.entity.*;
import com.example.CompuCom2.model.ProductCategoryModel;
import com.example.CompuCom2.model.ShoppingCartModel;
import com.example.CompuCom2.model.UserAddressModel;
import com.example.CompuCom2.repository.UserRepository;
import com.example.CompuCom2.service.ProductCategoryService;
import com.example.CompuCom2.service.impl.ProductServiceImpl;
import com.example.CompuCom2.service.impl.ShoppingCartServiceImpl;
import com.example.CompuCom2.service.impl.UserServiceImpl;
import lombok.Builder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping("/shopping_cart")
public class SaleController {
    private static final Log LOG = LogFactory.getLog(SaleController.class);

    @Autowired
    @Qualifier("shoppingCartServiceImpl")
    private ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductServiceImpl productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    @Autowired
    @Qualifier("userAddressConverter")
    private UserAddressConverter userAddressConverter;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @RequestMapping("/showcart")
    public ModelAndView showTheShoppingCart(Integer id_user, @RequestParam(name = "result", required = false)
                                            Integer result, @RequestParam(name = "deleted", required = false)
                                            Integer deleted){
        ModelAndView mav = new ModelAndView(Constants.SHOPPING_CART);
        ArrayList<ShoppingCartModel> shopping_cart = shoppingCartService.findAllProductsByUser(id_user);

//        We verify if the availability of each product corresponds to the quantity of the S.Cart of the customer:
        ArrayList<String> messages = check_availability(id_user, shopping_cart);
        if (messages.size() != 0){
            mav.addObject("messages", messages);
//            With the changes occurred, We update the shopping_cart
            shopping_cart = shoppingCartService.findAllProductsByUser(id_user);
        }

        mav.addObject("shopping_cart", shopping_cart);
        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        mav.addObject("categories", productCategoryModels);
        mav.addObject("result", result);
        mav.addObject("deleted", deleted);
        return mav;
    }

    private ArrayList<String> check_availability(int id_user, ArrayList<ShoppingCartModel> shopping_cart) {
        ArrayList<String> messages = new ArrayList<>();
        for (ShoppingCartModel sc : shopping_cart) {
            int customer_quantity = sc.getQuantity();
            int products_in_stock = sc.getProduct().getProductQuantityModel().getQuantity();
            if (products_in_stock == 0){
                messages.add("'" + sc.getProduct().getName() + "' eliminado del carrito. Disponibles actualmente en stock: 0 unidades.");
                deleteFromShoppingCart(id_user, sc.getProduct().getId());
            }else if (products_in_stock < customer_quantity){
                messages.add("'" + sc.getProduct().getName() + "' cantidad de productos modificada. Disponibles actualmente en stock: " + products_in_stock + " unidades.");
                // We establish the maximum amount of products available in Stock
                updateQuantity(sc.getIdSc(), products_in_stock);
            }
        }
        return messages;
    }

    @RequestMapping("/addtoshoppingcart")
    public ModelAndView addProductToShoppingCart(Integer id_user, Integer id_prod){
        LOG.info("METHOD: addProductToShoppingCart() --PARAM: id_user="+id_user+", id_prod="+id_prod);
        ModelAndView mav = new ModelAndView("redirect:/shopping_cart/showcart?id_user=" + id_user);
        boolean result = shoppingCartService.addProductToSC(id_user, id_prod);
        if(result){
            mav.addObject("result", 1);
        }else{
            mav.addObject("result", 0);
        }
        return mav;
    }

    @RequestMapping("/deletefromshoppingcart")
    public ModelAndView deleteFromShoppingCart(Integer id_user, Integer id_prod){
        LOG.info("METHOD: deleteFromShoppingCart() --PARAM: id_user="+id_user+", id_prod="+id_prod);
        ModelAndView mav = new ModelAndView("redirect:/shopping_cart/showcart?id_user=" + id_user);
        int deleted = shoppingCartService.removeProductFromSC(id_user, id_prod);
        System.out.println("deleted="+deleted);
        mav.addObject("deleted", deleted);
        return mav;
    }

    @ResponseBody
    @GetMapping("/getnumberofproducts")
    public int getNumberOfProducts(Integer id_user){
        LOG.info("METHOD: getNumberOfProducts() --PARAM: id_user="+id_user);
        return shoppingCartService.numberOfProducts(id_user);
    }

    @GetMapping("/updatequantity")
    public ModelAndView updateQuantity(int sc_id, int quantity){
        LOG.info("METHOD: updateQuantity() --PARAM: id_sc="+sc_id+", quantity="+quantity);
        ShoppingCart sc = shoppingCartService.modifyQuantity(sc_id, quantity);
        return new ModelAndView("redirect:/shopping_cart/showcart?id_user=" + sc.getIdUser());
    }

    @RequestMapping("/shipping_method")
    public ModelAndView shippingMethod(Integer id_user){
        LOG.info("METHOD: updateQuantity() --PARAM: id_sc="+id_user);
        ModelAndView mav = new ModelAndView(Constants.SHIPPING_METHOD);
        UserAddressModel userAddressModel = userService.findUserAddressByIdModel(id_user);
        mav.addObject("address", userAddressModel);
        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        mav.addObject("categories", productCategoryModels);
        return mav;
    }

    @RequestMapping("/payment_method")
    public ModelAndView paymentMethod(){
        return new ModelAndView(Constants.PAYMENT_METHOD);
    }


    @GetMapping("/test")
    public ModelAndView test(){
        Details details = new Details(1,"PC",123d,40d,2);
        Details details1 = new Details(2,"DD",1234d,39d,1);
        Status_shipping status_shipping = new Status_shipping("Pendiente", LocalDate.now());

        ArrayList<Details> bill_details = new ArrayList<>();
        bill_details.add(details);
        bill_details.add(details1);

        Shipping shipping = new Shipping("street", "12", "colony", "Ags", "Ags", "20000", "Mexico", "reference", status_shipping);
        Bill bill = new Bill(100d, 900d, 1000d, bill_details, shipping);

        User user = userService.findUserByUsername("user1");
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(bill);

        user.setBills(bills);
        userRepository.save(user);

        return new ModelAndView("redirect:/index");
    }
}
