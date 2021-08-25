package com.p2p.p4f.server;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;


//"jdbc:sqlserver://NChinh-Laptop\\SQLEXPRESS;databaseName=PrayForFood;integratedSecurity=true;"

public final class ConnectionPool {
    private static volatile ConnectionPool instance = null;
    private static final BasicDataSource sDS = new BasicDataSource();
    private static final int  MinIdleConnectionInPool = 2;
    private static final int MaxIdleConnectionInPool = 5;
    private ConnectionPool(){}

    public ConnectionPool getInstance() {
        System.out.println("Create Instance");
        if (instance == null){
            synchronized (ConnectionPool.class){
                if (instance == null){
                    instance = new ConnectionPool();
                }
            }
        }
        sDS.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        sDS.setMinIdle(MinIdleConnectionInPool);
        sDS.setInitialSize(MinIdleConnectionInPool);
        sDS.setMaxIdle(MaxIdleConnectionInPool);
        sDS.setMaxOpenPreparedStatements(100);
        return instance;
    }

    public static void setFirstUrl(String url){
        sDS.setUrl(url);
    }
    public static Connection getConnection() throws SQLException {
        return sDS.getConnection();
    }
}



