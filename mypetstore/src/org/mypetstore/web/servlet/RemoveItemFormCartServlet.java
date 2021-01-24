package org.mypetstore.web.servlet;

import org.mypetstore.domain.Account;
import org.mypetstore.domain.Cart;
import org.mypetstore.domain.Item;
import org.mypetstore.domain.Log;
import org.mypetstore.service.CartService;
import org.mypetstore.service.CatalogService;
import org.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveItemFormCartServlet extends HttpServlet {

    //跳转页面
    private static final String VIEW_CART = "WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "WEB-INF/jsp/common/Error.jsp";

    //要处理的数据
    private String workingItemId;
    private Cart cart;
    private String userId;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");

        HttpSession session = req.getSession();
        cart = (Cart)session.getAttribute("cart");
        Account account = (Account)session.getAttribute("account");
        if(account != null){
            userId = account.getUsername();
        }
        CartService cartService = new CartService();
        cartService.setCart(cart);

        int isRemoved = cartService.removeCarItem(workingItemId,userId);
        if(isRemoved == 0)  {
            session.setAttribute("message", "Attempted to remove null CartItem from Cart.");
            if(account != null){
                HttpServletRequest httpRequest = req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 物品为空，不能移除";
                session.setAttribute("message",logInfo);
            }

            req.getRequestDispatcher(ERROR).forward(req, resp);
        }else{
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " " + workingItemId + " 已从购物车中移除";
                session.setAttribute("message",logInfo);
            }

            req.getRequestDispatcher(VIEW_CART).forward(req, resp);
        }
    }
}
