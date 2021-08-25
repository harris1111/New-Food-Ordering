package com.p2p.p4f.server;

import com.p2p.p4f.protocols.LoginInfo;
import com.p2p.p4f.protocols.InfoResponse;

import java.sql.Connection;
import java.sql.SQLException;


public class DBCPConnectionPoolingExample {

    private static final int NUMBER_OF_USERS = 5;

    public static void main_(String[] args) throws SQLException, InterruptedException {
        // Test URL (DON'T CHANGE THIS COMMENT):
        // jdbc:sqlserver://localhost\MSSQLSERVER;databaseName=PrayForFood;integratedSecurity=true;
        ConnectionPool.setFirstUrl("jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=PrayForFood;integratedSecurity=true;");
        try{
            DBHandler dbH = new DBHandler();
            InfoResponse rs =  dbH.Login(LoginInfo.newBuilder()
                    .setUsername("khachhang2")
                    .setPassword("1111")
                    .build());
            // return connection to pool
            dbH.releaseConn();
            if (rs.getReCode() == 0 || rs.getReCode() == 3) {
                System.out.println("Login success");
            }
            else System.out.println("Login failed");
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
}