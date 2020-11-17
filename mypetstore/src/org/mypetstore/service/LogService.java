package org.mypetstore.service;

import org.mypetstore.domain.Log;
import org.mypetstore.persistence.LogDAO;
import org.mypetstore.persistence.impl.LogImpl;

public class LogService {
    Log log;
    private LogDAO logDAO;

    public LogService(){
        log = new Log();
        logDAO = new LogImpl();
    }

    public String logInfo(Object ...s){
        return log.logInfomation(s);
    }

    public void insertLogInfo(String username, String logInfo){
        logDAO.insertLog(username, logInfo);
    }
}
