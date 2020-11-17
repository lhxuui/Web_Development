package org.mypetstore.persistence.impl;

import org.mypetstore.domain.Cart;
import org.mypetstore.domain.CartItem;
import org.mypetstore.domain.Item;
import org.mypetstore.persistence.CartDAO;
import org.mypetstore.persistence.DBUtil;
import org.mypetstore.persistence.ItemDAO;
import org.mypetstore.service.CatalogService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartImpl implements CartDAO {
    private static final String getCartItemListByUserIdString = "SELECT * FROM CARTITEM WHERE USERID = ?    ";
    private static final String removeItemByIdString = "DELETE FROM CARTITEM WHERE ITEMID = ? AND USERID = ?";
    private static final String addCartItemString = "INSERT INTO CARTITEM (ITEMID, QUANTITY, INSTOCK, USERID) VALUES (?,?,?,?)";
    private static final String updateQuantityString = "UPDATE CARTITEM SET QUANTITY = ? WHERE USERID = ? AND ITEMID = ?";
    private static final String isExistsString = "SELECT * FROM CARTITEM WHERE USERID = ? AND ITEMID = ?";
    private static final String incrementQuantityString = "UPDATE CARTITEM SET QUANTITY = QUANTITY + 1 WHERE USERID = ? AND ITEMID = ?";
    @Override
    public List<CartItem> getCartItemListByUserId(String userId) {
        List<CartItem> cartItemList = new ArrayList<CartItem>();
        CatalogService catalogService = new CatalogService();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getCartItemListByUserIdString);
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                CartItem cartItem = new CartItem();
                Item item = catalogService.getItem(resultSet.getString(1));
                cartItem.setItem(item);
                cartItem.setQuantity(resultSet.getInt(2));
                cartItem.setInStock(resultSet.getInt(3) == 1);
                cartItem.setUserID(userId);
                cartItemList.add(cartItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cartItemList;
    }


    @Override
    public int removeCartItem(String itemId, String userId){
        int result = 0;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(removeItemByIdString);
            preparedStatement.setString(1,itemId);
            preparedStatement.setString(2,userId);
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void addCartItem(Item item ,Boolean inStock ,String userId) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(addCartItemString);
            preparedStatement.setString(1,item.getItemId());
            preparedStatement.setInt(2,0);
            preparedStatement.setInt(3,inStock ? 1 : 0);
            preparedStatement.setString(4,userId);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int updateQuantity(String itemId, int quantity, String userId){
        int result = -1;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuantityString);
            preparedStatement.setInt(1,quantity);
            preparedStatement.setString(2,userId);
            preparedStatement.setString(3,itemId);
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isExists(String itemId, String userId) {
        boolean result = false;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(isExistsString);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = true;
            }
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void incrementQuantity(String itemId,String userId){
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(incrementQuantityString);
            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,itemId);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
