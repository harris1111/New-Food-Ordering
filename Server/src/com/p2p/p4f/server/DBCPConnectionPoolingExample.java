package com.p2p.p4f.server;

import java.sql.Connection;
import java.sql.SQLException;



public class DBCPConnectionPoolingExample {

    private static final int NUMBER_OF_USERS = 5;

    public static void main_2(String[] args) throws SQLException, InterruptedException {
        ConnectionPool.setFirstUrl("jdbc:sqlserver://NChinh-Laptop\\SQLEXPRESS;databaseName=PrayForFood;integratedSecurity=true;");
        Thread T1 = new Thread(()->{
            try(Connection conn = ConnectionPool.getConnection();
                ){
                DBHandler dbH = new DBHandler(conn);
            System.out.println("Thread 1 " + dbH.Login("khachhang2","pss"));
            conn.close();}
            catch(SQLException e){
                e.printStackTrace();
            }
        });
        Thread T2 = new Thread(()->{
            try(Connection conn = ConnectionPool.getConnection();
            ){
                DBHandler dbH = new DBHandler(conn);
                System.out.println("Thread 2 " + dbH.Login("khachhang3","pss"));
                conn.close();}
            catch(SQLException e) {
                e.printStackTrace();
            }
        });
        T1.start();
        T2.start();
        T1.join();
        T2.join();
    }
}