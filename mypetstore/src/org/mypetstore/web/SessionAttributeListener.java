package org.mypetstore.web;

import org.mypetstore.domain.Account;
import org.mypetstore.service.LogService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {
    LogService logService = new LogService();
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        String attributeValue = (String)se.getValue();
        Account account = (Account)se.getSession().getAttribute("account");
        String userName = account.getUsername();
        String attributeName = se.getName();
        if(attributeName.equals("message")){
            logService.insertLogInfo(userName, attributeValue);
        }
    }
}
