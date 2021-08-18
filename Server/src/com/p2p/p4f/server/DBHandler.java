package com.p2p.p4f.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
import java.sql.ResultSet;

class DBHandler extends Thread {

    public ResultSet rs = null;
    private CountDownLatch latch;
    private String taskName;
    // work có 3 trường hợp, Login, Reg, và getBranch
    public DBHandler(CountDownLatch latch, String taskName, String work[]) {
        this.latch = latch;
        this.taskName = taskName;
        switch (work[0]){
            case "Login":
                System.out.println("Check user: " + CheckUser(work[1], work[2]));
                break;
            case "Reg":
                System.out.println("Insert user: " + InsertUser(work[1], work[2]));
                break;
            case "getBranch":
                rs = getBranch();
                System.out.println("Get branch successfully");
                break;
        }
    }


    private boolean CheckUser(String user, String pass) {
        System.out.println(Thread.currentThread().getName() + " Starting. Task = " + taskName);
        String sqlState = "select * from tblUser u where u.Username = \'" + user+"\'";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlState);) {
            Thread.sleep(2000);
            rs.next();
            System.out.println("Task = " + taskName + ": Run SQL successfully ");
            if (pass.equals(rs.getString("U_pass"))) {
                latch.countDown();
                System.out.println(Thread.currentThread().getName() + " Finished.");
                return true;
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
        System.out.println(Thread.currentThread().getName() + " Finished.");
        return false;
    }


    private boolean InsertUser(String user, String U_passw) {
        System.out.println(Thread.currentThread().getName() + " Starting. Task = " + taskName);
        String sqlState = "insert into tblUser(Username, U_pass, Usertype, U_address) \nvalues";
        String val = "(\'" + user + "\', " + "\'" + U_passw + "\', '1', 'ABCCCCC')";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement st = conn.createStatement();
        )
                { st.executeUpdate(sqlState+val);
            Thread.sleep(2000);
            System.out.println("Task = " + taskName + ": Run SQL successfully ");
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " Finished.");
            return true;
                } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

        latch.countDown();
        System.out.println(Thread.currentThread().getName() + " Finished.");
        return false;
    }

    // ResultSet: hàm getArray() để lấy từng cột, có 6 cột
    // Branch_ID, Branch_Name, Branch_Address, Branch_image, Branch_Location_Longtitude, Branch_Location_Latitude
    // ví dụ: rs.getArray("BranchID") là lấy cột ID của toàn bộ branch
    private ResultSet getBranch() {
        System.out.println(Thread.currentThread().getName() + " Starting. Task = " + taskName);
        try {
            String sqlSelect = "SELECT * from tblBranch";
            try (
                    Connection conn = ConnectionPool.getConnection();
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(sqlSelect);) {
                Thread.sleep(2000);
                rs.next();
                System.out.println("Task = " + taskName + ": Run SQL successfully ");
                latch.countDown();
                System.out.println(Thread.currentThread().getName() + " Finished.");
                return rs;
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
        System.out.println(Thread.currentThread().getName() + " Finished.");
        return null;
    }
}


