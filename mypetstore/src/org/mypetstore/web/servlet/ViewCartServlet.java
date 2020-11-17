package org.mypetstore.web.servlet;

import org.mypetstore.domain.Account;
import org.mypetstore.domain.Cart;
import org.mypetstore.service.CartService;
import org.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewCartServlet extends HttpServlet {
    private static final String VIEW_CART = "WEB-INF/jsp/cart/Cart.jsp";

    private String userId;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart =(Cart)session.getAttribute("cart");
        CartService cartService = new CartService();
        Account account = (Account)session.getAttribute("account");
        if(account != null){
            userId = account.getUsername();
        }
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cartService.setCart(cart);
        cartService.getAllCartItem(userId);
        session.setAttribute("cart",cart);

        req.getRequestDispatcher(VIEW_CART).forward(req,resp);
    }
}
