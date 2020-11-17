package org.mypetstore.persistence;

import org.mypetstore.domain.CartItem;
import org.mypetstore.domain.Item;

import java.util.List;
import java.util.Map;

public interface CartDAO {
    List<CartItem> getCartItemListByUserId(String userId);
    void addCartItem(Item item, Boolean inStock, String userId);
    int updateQuantity(String itemId, int quantity, String userId);
    int removeCartItem(String itemId, String userId);
    boolean isExists(String itemId, String userId);
    void incrementQuantity(String itemId, String userId);
}
