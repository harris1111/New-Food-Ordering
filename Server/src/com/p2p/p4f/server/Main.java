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
    
    public static void main(String[] args) {
        try {
            String dbConnString = "jdbc:sqlserver://DESKTOP-QUAL6L1\\SQLEXPRESS;databaseName=PrayForFood;integratedSecurity=true;";
            ServerP4F server = new ServerP4F(dbConnString, "192.168.5.115", 9999);
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
