package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.entity.*;
import com.example.CompuCom2.model.ProductCategoryModel;
import com.example.CompuCom2.model.ProductModel;
import com.example.CompuCom2.model.ShoppingCartModel;
import com.example.CompuCom2.model.UserAddressModel;
import com.example.CompuCom2.repository.UserRepository;
import com.example.CompuCom2.service.ProductCategoryService;
import com.example.CompuCom2.service.impl.ProductQuantityServiceImpl;
import com.example.CompuCom2.service.impl.ProductServiceImpl;
import com.example.CompuCom2.service.impl.ShoppingCartServiceImpl;
import com.example.CompuCom2.service.impl.UserServiceImpl;
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

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shopping_cart")
public class ShoppingController {
    private static final Log LOG = LogFactory.getLog(ShoppingController.class);

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
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("productQuantityServiceImpl")
    private ProductQuantityServiceImpl productQuantityService;

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

        UserAddressModel address = userService.findUserAddressByIdModel(id_user);

        mav.addObject("shopping_cart", shopping_cart);
        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        mav.addObject("address", userService.findUserAddressByIdModel(id_user));
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

    @GetMapping("/finish_sale")
    public ModelAndView finishTheSale(@RequestParam(name = "id_user") Integer id_user){
        LOG.info("METHOD: saveBill() --PARAMS: id_user="+id_user);
        ModelAndView mav = new ModelAndView(Constants.GRATITUDE);
        User user = userRepository.findById(id_user);
        System.out.println("USER=" + user);

        List<Details> details = getDetails(user);
        Shipping shipping = getShippingInfo(user);
        Bill bill = getBillInfo(user, details, shipping);
        saveBill(user, bill);
        cleanShoppingCart(user);

        ArrayList<ProductCategoryModel> productCategoryModels = (ArrayList<ProductCategoryModel>) productCategoryService.findAll();
        mav.addObject("categories", productCategoryModels);
        return mav;
    }

    private void cleanShoppingCart(User user) {
        LOG.info("METHOD: cleanShoppingCart() --PARAMS: user="+user);
        int result = shoppingCartService.removeAllProductsByUser(user.getId());
    }

    private void saveBill(User user, Bill bill) {
        LOG.info("METHOD: saveBill() --PARAMS: user="+user+", bill="+bill);
        List<Bill> bills_of_user = user.getBills();
//       We set manually the 'id' of the User propietary of this Bill:
        bill.setUserPropietary_id(user.getId());
        bills_of_user.add(bill);
        user.setBills(bills_of_user);
        userRepository.save(user);
    }

    // We get the information of each product as the 'Details of the Bill':
    public List<Details> getDetails(User user) {
        LOG.info("METHOD: getDetails() ---PARAMS: UserModel="+user);
        ArrayList<Details> details = new ArrayList<>();
        // We search all the products from that User in his/her S.Cart:
        ArrayList<ShoppingCartModel> SCUser = shoppingCartService.findAllProductsByUser(user.getId());
        // For each one, We added to a Details' Arraylist and subtract the inventory:
        for (ShoppingCartModel sc : SCUser) {
            DecimalFormat formatter = new DecimalFormat("#0.00");
            Double price = Double.parseDouble(formatter.format((sc.getProduct().getPrice() * 0.84)));
            details.add(new Details(sc.getProduct().getId(), sc.getProduct().getName(), price,
                    sc.getProduct().getDiscount().getPercentage(), sc.getQuantity()));
            subtract_products(sc.getProduct().getId(), sc.getQuantity());
        }
        return details;
    }

    // We update the inventory of products:
    private void subtract_products(Integer id_prod, int quantity) {
        LOG.info("METHOD: subtract_products() --PARAMS: id_prod="+id_prod+", quantity="+quantity);
        ProductModel product = productService.getProductById(id_prod);
        int available = product.getProductQuantityModel().getQuantity();
        product.getProductQuantityModel().setQuantity((available-quantity));
        productQuantityService.saveQuantity(product.getProductQuantityModel());
    }


    // We get the information of the Shipping:
    private Shipping getShippingInfo(User user) {
        LOG.info("METHOD: getShippingInfo() --PARAMS: UserModel="+user);
        UserAddress address = user.getUserAdress();
        return new Shipping(
                address.getStreet(), address.getNumber(),
                address.getColony(), address.getCity(),
                address.getState(), address.getZip(),
                address.getCountry(), address.getReference(),
                new StatusShipping("Pendiente", LocalDateTime.now()));
    }

    private Bill getBillInfo(User user, List<Details> details, Shipping shipping) {
        Double shipping_cost;
        // To set the Shipping Price We need to know from Where is the user, if he/she is from Ags the cost is $0
        // else if he/she is from any other city from Mexico, the cost is $150
        if(user.getUserAdress().getCity().toUpperCase().contains("AGUASCALIENTES")){
            shipping_cost = 0.0d;
        }else if(user.getUserAdress().getCountry().toUpperCase().contains("MEXICO")){
            shipping_cost = 150d;
        }else{
            shipping_cost = 350d;
        }

        // Now We calculate the Subtotal and Total of all the products purchased:
        Double total = 0.0d;
        Double iva = 0.0d;
        Double subtotal = 0.0d;

        for (Details product : details){
            Double sub = product.getPrice() * product.getQuantity();
            subtotal += sub - (sub * (product.getDiscount() / 100));
        }
        DecimalFormat formatter = new DecimalFormat("#0.00");
        subtotal = Double.parseDouble(formatter.format(subtotal));
        iva = subtotal * 0.16;
        iva = Double.parseDouble(formatter.format(iva));
        total = (subtotal + iva + shipping_cost);
        total = Double.parseDouble(formatter.format(total));
        // ends the calculatios

        return new Bill(shipping_cost, iva, subtotal, total, LocalDateTime.now(), details, shipping);
    }
}
