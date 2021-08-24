package com.p2p.p4f.server;

import java.sql.Connection;
import java.sql.SQLException;


public class DBCPConnectionPoolingExample {

    private static final int NUMBER_OF_USERS = 5;

    public static void main(String[] args) throws SQLException, InterruptedException {
        ConnectionPool.setFirstUrl("jdbc:sqlserver://NChinh-Laptop\\SQLEXPRESS;databaseName=PrayForFood;integratedSecurity=true;");
            try(Connection conn = ConnectionPool.getConnection();
                ){
                DBHandler dbH = new DBHandler(conn);
                Int_User rs =  dbH.Login("khachhang2","pss");
            System.out.println("Thread 1 " + rs.getInt());
                System.out.println("Thread 1 " + rs.u.username);
                dbH.getNewConn();
                User newU = new User (rs.u);
                newU.email = "kh2@mail.vn";
            System.out.println("Thread 1 " + dbH.ChangeInformation(rs.u,rs.u.pass,newU));
                }
            catch(SQLException e){
                e.printStackTrace();
            }

    }
}