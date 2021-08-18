package com.p2p.p4f.server;

import java.sql.Connection;
import java.sql.SQLException;



public class DBCPConnectionPoolingExample {

    private static final int NUMBER_OF_USERS = 5;

    public static void main(String[] args) throws SQLException, InterruptedException {
        ConnectionPool.setFirstUrl("jdbc:sqlserver://NChinh-Laptop\\SQLEXPRESS;databaseName=PrayForFood;integratedSecurity=true;");
        Connection conn = ConnectionPool.getConnection();
        DBHandler dbH = new DBHandler(conn);
        dbH.get_ListRestaurant();
        conn.close();
    }
}