package org.mypetstore.web.servlet;

import com.alibaba.fastjson.JSONObject;
import org.mypetstore.domain.Account;
import org.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CheckUsernameServlet")
public class CheckUsernameServlet  extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("username");
        Account account = new Account();
        account.setUsername(username);
        AccountService accountService = new AccountService();
        JSONObject jsonObject = new JSONObject();
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        if(accountService.getAccount(account.getUsername()) != null){
            jsonObject.put("msg","exist");
        }
        else {
            jsonObject.put("msg","notexist");
        }
        out.write(String.valueOf(jsonObject));
        out.flush();
        out.close();
    }
}
