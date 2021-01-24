package org.mypetstore.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

import org.mypetstore.domain.Account;
import org.mypetstore.service.LogService;

public class LogFilter implements Filter {

    private LogService logService;

    @Override
    public void init(FilterConfig arg0) throws ServletException{
        System.out.println("Logfilter initialized");
        try{
            logService = new LogService();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        try{
            Account account =(Account) session.getAttribute("account");
            if(account != null) {
                String loginfo = (String)session.getAttribute("message");
                logService.insertLogInfo(account.getUsername(),loginfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
