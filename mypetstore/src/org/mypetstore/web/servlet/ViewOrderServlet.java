package org.mypetstore.web.servlet;


import org.mypetstore.domain.Account;
import org.mypetstore.domain.Cart;
import org.mypetstore.domain.Order;
import org.mypetstore.service.LogService;
import org.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderServlet extends HttpServlet {
    private static final String VIEWORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private Order order;
    private OrderService orderService;
    private Cart cart;

    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        doGet(req, response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = req.getSession();
        order = (Order) session.getAttribute("order");
        cart = (Cart) session.getAttribute("cart");

        if (order != null) {
            orderService = new OrderService();
            orderService.insertOrder(order);
            session.setAttribute("order", order);
            //清空购物车
            cart = null;
            session.setAttribute("cart", cart);

            session.setAttribute("message", "Thank you, your order has been submitted.");

            //HttpSession session = req.getSession();
            Account account = (Account)session.getAttribute("account");

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 查看订单 " + order;
                session.setAttribute("message",logInfo);

            }

            req.getRequestDispatcher(VIEWORDER).forward(req, response);
        } else {
            session.setAttribute("message", "An error occurred processing your order (order was null).");
            req.getRequestDispatcher(ERROR).forward(req, response);
        }
    }
}
