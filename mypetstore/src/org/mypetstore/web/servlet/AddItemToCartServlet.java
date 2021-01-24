package org.mypetstore.web.servlet;

import org.mypetstore.domain.Account;
import org.mypetstore.domain.Cart;
import org.mypetstore.domain.Item;
import org.mypetstore.domain.Sequence;
import org.mypetstore.service.CartService;
import org.mypetstore.service.CatalogService;
import org.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AddItemToCartServlet extends HttpServlet {

    //跳转页面
    private static final String VIEW_CART = "WEB-INF/jsp/cart/Cart.jsp";

    //要处理的数据
    private String workingItemId;
    private CatalogService catalogService;
    private Cart cart;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");

        HttpSession session = req.getSession();
        cart = (Cart)session.getAttribute("cart");

        if(cart == null){
            cart = new Cart();
        }

        Account account = (Account)session.getAttribute("account");
        String userId = account.getUsername();
        CartService cartService = new CartService();
        cartService.setCart(cart);

        if(cartService.containsItemId(workingItemId,userId)){
            cartService.incrementQuantity(workingItemId,userId);
            if(account != null){
                HttpServletRequest httpRequest= (HttpServletRequest) req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                Item item = catalogService.getItem(workingItemId);
                String logInfo = logService.logInfo(" ") + strBackUrl + " " + item + "数量+1 ";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
        }else{
            catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cartService.addCartItem(item, isInStock, userId);

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 添加物品 " + item + " 到购物车";
                session.setAttribute("message",logInfo);
            }
            session.setAttribute("cart", cart);
            req.getRequestDispatcher(VIEW_CART).forward(req, resp);
        }
    }
}
