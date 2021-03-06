package org.mypetstore.web.servlet;

import org.mypetstore.domain.Account;
import org.mypetstore.domain.Cart;
import org.mypetstore.domain.CartItem;
import org.mypetstore.service.CartService;
import org.mypetstore.service.CatalogService;
import org.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class UpdateCartQuantitiesServlet extends HttpServlet {

    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    private String workingItemId;
    private Cart cart;
    private String userId;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");
        CatalogService catalogService = new CatalogService();

        //从对话中，获取购物车
        HttpSession session = req.getSession();
        cart = (Cart)session.getAttribute("cart");

        Account account = (Account)session.getAttribute("account");
        if(account != null) {
            userId = account.getUsername();
        }
        CartService cartService = new CartService();
        cartService.setCart(cart);

        Iterator<CartItem> cartItemIterator = cart.getAllCartItems();

        while (cartItemIterator.hasNext()){
            //产品数量增加
            CartItem cartItem = (CartItem)cartItemIterator.next();
            String itemId = cartItem.getItem().getItemId();

            try {
                int quantity = Integer.parseInt((String) req.getParameter(itemId));
                cartService.setQuantity(itemId,quantity,userId);
                if (quantity < 1) {
                    cartService.removeCarItem(itemId,userId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        session.setAttribute("cart", cart);

        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 更新购物车商品数量";
            session.setAttribute("message",logInfo);

        }

        req.getRequestDispatcher(VIEW_CART).forward(req, resp);
    }
}
