package com.p2p.p4f.server;

import java.sql.*;
import java.util.ArrayList;

class User{
    public String username = "NULL";
    public String pass = "NULL";
    public int Usertype = 1;
    public String email = "NULL";
    public String phone ="NULL";
    public String addr = "NULL";
    public String image = "NULL";
    public User(){}
    public User(String usr, String pass, String addr){
        this.username = usr;
        this.pass = pass;
        this.addr = addr;
    }
    public User(String usr, String pass, String email, String phone, String addr, String image){
        this.username = usr;
        this.pass = pass;
        this.email = email;
        this.phone = phone;
        this.addr = addr;
        this.image = image;
    }
}

class Restaurant{
    public String Branch_ID,
            Branch_Name,
            Branch_Address,
            Branch_image,
            Branch_Location_Longtitude,
            Branch_Location_Latitude = "NULL";
    public Restaurant(){}
    public Restaurant(String Branch_ID,String Branch_Name,String Branch_Address,String Branch_image,
                      String Branch_Location_Longtitude, String Branch_Location_Latitude){
        this.Branch_ID = Branch_ID;
        this.Branch_Name = Branch_Name;
        this.Branch_Address = Branch_Address;
        this.Branch_image = Branch_image;
        this.Branch_Location_Longtitude = Branch_Location_Longtitude;
        this.Branch_Location_Latitude = Branch_Location_Latitude;
    }
}

public class DBHandler {
    private Connection conn;
    public DBHandler(Connection conn){
        this.conn = conn;
    }

    // 0 = no fault, 1 = username fault, 2 = pw fault
    public int Login(String user, String pass) {
        String sqlState = "select * from tblUser u where u.Username = \'" + user+"\'";
        try (   Connection conn = ConnectionPool.getConnection();
                PreparedStatement st = conn.prepareStatement(sqlState);
                ResultSet rs = st.executeQuery();
                ) {
            if (!rs.next()) return 1;
            if (!pass.equals(rs.getString("U_pass"))) {
                return 2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 0 = no fault, 1 = user fault, 2 = email fault, 3 = phone fault
    public int Register(User u) {
        String sqlState = "insert into tblUser(Username, U_pass, Usertype, " +
                "Email, Phone, U_address, U_image) \nvalues";
        if (!u.email.equals("NULL")) u.email = "\'" + u.email + "\'";
        if (!u.phone.equals("NULL")) u.phone = "\'" + u.phone + "\'";
        String val = "(\'" + u.username + "\'," +
                "\'" + u.pass + "\',"+
                "\'" + u.Usertype +"\'," +
                u.email + ","  +
                u.phone + "," +
                "N\'" + u.addr +
                "\', " + u.image + ");" ;
        String QState = "select * from tblUser where ? = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                //PreparedStatement st = conn.prepareStatement(sqlState+val);
                Statement st = conn.createStatement();

                PreparedStatement stQuery = conn.prepareStatement(QState);
                )
                {
                    stQuery.setString(1,"u.Username");
                    stQuery.setString(2,"\'" + u.username + "\'");
                    ResultSet rs = stQuery.executeQuery();
                    if (rs.next()) return 1;
                    stQuery.setString(1,"u.Email");
                    stQuery.setString(2,"\'" + u.email + "\'");
                    if (!u.email.equals("NULL")) {
                        rs = stQuery.executeQuery();
                        if (rs.next()) return 2;
                    }
                    stQuery.setString(1,"u.Phone");
                    stQuery.setString(2,"\'" + u.phone + "\'");
                    if (!u.phone.equals("NULL")) {
                        rs = stQuery.executeQuery();
                        if (rs.next()) return 3;
                    }
                    st.executeUpdate(sqlState + val);
                    return 0;
                } catch (SQLException e) {
            e.printStackTrace();
        }
        return 5;
    }

    // ResultSet: hàm getArray() để lấy từng cột, có 6 cột
    // Branch_ID, Branch_Name, Branch_Address, Branch_image, Branch_Location_Longtitude, Branch_Location_Latitude
    // ví dụ: rs.getArray("BranchID") là lấy cột ID của toàn bộ branch
    public ArrayList<Restaurant> get_ListRestaurant() {
        try {
            String sqlSelect = "SELECT * from tblBranch";
            ArrayList<Restaurant> list = new ArrayList<>();
            try (
                    Connection conn = ConnectionPool.getConnection();
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(sqlSelect);) {
                    while(rs.next()){
                        Restaurant r = new Restaurant(rs.getString("Branch_ID"),
                                rs.getString("Branch_Name")
                                ,rs.getString("Branch_Address")
                                ,rs.getString("Branch_image")
                        ,rs.getString("Branch_Location_Longitude")
                                ,rs.getString("Branch_Location_Latitude"));
                        list.add(r);
                    }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


