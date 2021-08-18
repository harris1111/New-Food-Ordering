package com.p2p.p4f.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;
import java.util.concurrent.CountDownLatch;




public class ConnectionPool {
    private static final BasicDataSource sDS = new BasicDataSource();
    private static final int  MinIdleConnectionInPool = 2;
    private static final int MaxIdleConnectionInPool = 5;
    private CountDownLatch latch;
        static {
            sDS.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            sDS.setUrl("jdbc:sqlserver://NChinh-Laptop\\SQLEXPRESS;databaseName=PrayForFood;integratedSecurity=true;");
            sDS.setMinIdle(MinIdleConnectionInPool);
            sDS.setInitialSize(MinIdleConnectionInPool);
            sDS.setMaxIdle(MaxIdleConnectionInPool);
            sDS.setMaxOpenPreparedStatements(100);
        }


    private ConnectionPool() {
        super();
    }

    public static Connection getConnection() throws SQLException {
        return sDS.getConnection();
    }

}



