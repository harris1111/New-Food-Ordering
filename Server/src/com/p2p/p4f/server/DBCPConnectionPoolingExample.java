package com.p2p.p4f.server;

import java.sql.Connection;
import java.sql.SQLException;


public class DBCPConnectionPoolingExample {

    private static final int NUMBER_OF_USERS = 5;

    public static void main_2(String[] args) throws SQLException, InterruptedException {
        // Test URL (DON'T CHANGE THIS COMMENT):
        // jdbc:sqlserver://localhost\MSSQLSERVER;databaseName=PrayForFood;integratedSecurity=true;
        ConnectionPool.setFirstUrl("jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=PrayForFood;integratedSecurity=true;");
        try{
                DBHandler dbH = new DBHandler();
                Int_User rs =  dbH.Login("khachhang2","pss");
            System.out.println("Thread 1 " + rs.getInt());
                System.out.println("Thread 1 " + rs.u.username);
                User newU = new User (rs.u);
                newU.email = "kh2@mail.vn";
            System.out.println("Thread 1 " + dbH.ChangeInformation(rs.u,rs.u.pass,newU));
            // return connection to pool
            dbH.releaseConn();
                }
            catch(SQLException e){
                e.printStackTrace();
            }

    }
}