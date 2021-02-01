package org.mypetstore.service;

import org.mypetstore.domain.Category;
import org.mypetstore.domain.Item;
import org.mypetstore.domain.Product;
import org.mypetstore.persistence.CategoryDAO;
import org.mypetstore.persistence.ItemDAO;
import org.mypetstore.persistence.ProductDAO;
import org.mypetstore.persistence.impl.CategoryImpl;
import org.mypetstore.persistence.impl.ItemImpl;
import org.mypetstore.persistence.impl.ProductImpl;

import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;

public class CatalogService {
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private ItemDAO itemDAO;

    public CatalogService(){
        categoryDAO = new CategoryImpl();
        productDAO = new ProductImpl();
        itemDAO = new ItemImpl();
    }


    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDAO.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDAO.getProductListByCategory(categoryId);
    }

    public List<Product> searchProductList(String keyword) {
        return productDAO.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
    }
}
