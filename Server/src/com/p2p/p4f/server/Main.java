package com.p2p.p4f.server;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;
import java.nio.channels.FileChannel;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public void testJDBC() {
        try {
            String connString = "jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=QuanLyDeAn;integratedSecurity=true";
            Connection conn = DriverManager.getConnection(connString);
            PreparedStatement stmt = conn.prepareStatement("SELECT HONV + ' ' + TENLOT + ' ' + TENNV FROM NHANVIEN");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void testServerReactor(int nThreads, int port, String ip) {
        try {
            final ServerReactor sv = new ServerReactor(nThreads, port, ip, "E:/logs.txt");
            Thread t1 = new Thread(
                    () -> {
                        try {
                            sv.start();
                        }
                        catch (Exception e) {
                            ServerReactor.log.log(Level.INFO, "Error!", e);
                        }
                    }
            );
            Thread t2 = new Thread(
                () -> {
                    try (DataInputStream sin = new DataInputStream(System.in)) {
                        while (!Thread.interrupted()) {
                            if (sin.available() > 4) {
                                int input = sin.readInt();
                                if (input == 0) {
                                    sv.close();
                                    break;
                                }
                            }
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Error occured!");
                    }
                }
            );
            t1.start();
            t2.start();
            t1.join();
            if (!t1.isAlive()) {
                System.out.println("Server thread stopped!");
                t2.interrupt();
            }
        }
        catch (Exception e) {
            System.out.println("Invalid bind address or logging path.");
        }
    }
    
    public static void main_(String[] args) {
        try {
            ServerP4F server = new ServerP4F("", 10201);
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
