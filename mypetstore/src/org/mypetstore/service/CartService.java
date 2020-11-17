package org.mypetstore.service;

import org.mypetstore.domain.Cart;
import org.mypetstore.domain.CartItem;
import org.mypetstore.domain.Item;
import org.mypetstore.persistence.CartDAO;
import org.mypetstore.persistence.impl.CartImpl;

import java.math.BigDecimal;
import java.util.*;

public class CartService {
    Cart cart;
    CartDAO cartDAO;

    public CartService(){
        cartDAO = new CartImpl();
        cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void incrementQuantity(String itemId, String userId){
        cartDAO.incrementQuantity(itemId,userId);
        cart.incrementQuantityByItemId(itemId);
    }

    public boolean containsItemId(String itemId,String userId){
        return cartDAO.isExists(itemId,userId);
    }

    public void addCartItem(Item item, Boolean inStock, String userId){
        cartDAO.addCartItem(item,inStock,userId);
        cart.addItem(item,inStock);
    }

    public void getAllCartItem(String userId){
        if(cartDAO.getCartItemListByUserId(userId) != null)
        cart.addItem(cartDAO.getCartItemListByUserId(userId));
    }

    public void setQuantity(String itemId, int quantity, String userId){
        cartDAO.updateQuantity(itemId, quantity, userId);
        cart.setQuantityByItemId(itemId,quantity);
    }

    public int removeCarItem(String itemId, String userId){
        cart.removeItemById(itemId);
        return cartDAO.removeCartItem(itemId,userId);
    }
}
