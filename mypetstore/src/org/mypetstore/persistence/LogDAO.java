package org.mypetstore.persistence;

public interface LogDAO {
    void insertLog(String username, String logInfo);
}
