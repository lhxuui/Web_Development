package org.mypetstore.web.servlet;

import org.mypetstore.domain.Account;
import org.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShippingFormServlet extends HttpServlet {
    private static final String SHIPPINGFORM = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignonForm.jsp";

    private Account account;

    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        doGet(req, response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = req.getSession();
        account = (Account)session.getAttribute("account");

        if (account == null){
            req.getRequestDispatcher(SIGNONFORM).forward(req, response);
        } else{
            //HttpSession session = req.getSession();
            Account account = (Account)session.getAttribute("account");

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 跳转到修改地址界面";
                session.setAttribute("message",logInfo);

            }

            req.getRequestDispatcher(SHIPPINGFORM).forward(req, response);
        }
    }
}
