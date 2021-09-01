package com.p2p.p4f.server;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Input database connection: ");
            String dbConnString = sc.nextLine();
            System.out.print("Input bind IP: ");
            String ip = sc.nextLine();
            ServerP4F server = new ServerP4F(dbConnString, ip, 9999);
            //String dbConnString = "jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=PrayForFood;integratedSecurity=true;";
            //ServerP4F server = new ServerP4F(dbConnString, "192.168.1.2", 9999);
            server.start();
            int code = sc.nextInt();
            if (code == 0)
                server.closeServer();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
