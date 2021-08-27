package com.p2p.p4f.server;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        try {
            String dbConnString = "jdbc:sqlserver://DESKTOP-7I3LBF0\\SQLEXPRESS;databaseName=PrayForFood;integratedSecurity=true;";
            ServerP4F server = new ServerP4F(dbConnString, "192.168.1.11", 9999);
            server.start();
            Scanner sc = new Scanner(System.in);
            int code = sc.nextInt();
            if (code == 0)
                server.closeServer();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
