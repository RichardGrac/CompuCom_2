package com.example.CompuCom2.service;

    import com.example.CompuCom2.entity.ShoppingCart;
    import com.example.CompuCom2.model.ShoppingCartModel;

    import java.util.ArrayList;

public interface ShoppingCartService {
//    Find all the products in the S.C. from the user: 'id'
    ArrayList<ShoppingCartModel> findAllProductsByUser(int id);
//    Method to add a product to the Shopping Cart
    boolean addProductToSC(int id_user, int id_prod);
//     Method to remove a Product from the Shopping Cart
    int removeProductFromSC(int id_user, int id_prod);
    ShoppingCart findByUserAndProduct(int id_user, int id_prod);
    int numberOfProducts(int id_user);
    ShoppingCart modifyQuantity(int id_sc, int quantity);
}
