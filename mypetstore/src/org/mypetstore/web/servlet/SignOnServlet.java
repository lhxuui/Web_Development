package org.mypetstore.web.servlet;

import org.mypetstore.domain.Account;
import org.mypetstore.service.AccountService;
import org.mypetstore.service.LogService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOnServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignonForm.jsp";

    private Account account;
    private AccountService accountService;

    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        doGet(req, response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        accountService = new AccountService();
        account = accountService.getAccount(username, password);

        HttpSession session = req.getSession();
        session.setAttribute("account", account);

        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath();

            String logInfo = strBackUrl + " 用户登录";
            session.setAttribute("message", logInfo);
        }

        //获得输入的验证码值
        String value1=req.getParameter("vCode");
        /*获取图片的值*/
        String value2=(String)session.getAttribute("checkcode");
        Boolean isSame = false;
        /*对比两个值（字母不区分大小写）*/
        if(value2.equalsIgnoreCase(value1)){
            isSame = true;
        }


        if (account == null || !isSame){
            if(!isSame){
                session.setAttribute("message", "Invalid Verification Code.   Signon failed.");
            }else{
                session.setAttribute("message", "Invalid username or password.  Signon failed.");
            }
            session.setAttribute("account", null);
            req.getRequestDispatcher(SIGNONFORM).forward(req, response);
        }else{
            account.setPassword(null);
            req.getRequestDispatcher(MAIN).forward(req, response);
        }
    }
}
