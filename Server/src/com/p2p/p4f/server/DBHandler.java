package com.p2p.p4f.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

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
    public User(Object o){
        this.username = ((User) o).username;
        this.pass = ((User) o).pass;
        this.email = ((User) o).email;
        this.phone = ((User) o).phone;
        this.addr = ((User) o).addr;
        this.image = ((User) o).image;
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

class Int_User{
    public int i;
    public User u;
    public Int_User(){}
    public Int_User(int i, User u){
        this.i = i;
        this.u = u;
    }
    public int getInt() {return this.i;}
    public User getUser(){return this.u;}
    public void setInt(int i){this.i = i;}
    public void setUser(User u){this.u = u;}
}

//Each time using the new query, you have to create new dbhandler
public class DBHandler {
    private Connection conn;
    public DBHandler(Connection conn){
        this.conn = conn;
    }


    // list[0]:  0 = user login, 1 = username fault, 2 = pw fault, 3 = staff login, 4 = other fault
    // list[1]:  User information
    public Int_User Login(String user, String pass) {
        String sqlState = "select * from tblUser u where u.Username = \'" + user+"\'";
        User u = new User();
        Int_User Result = new Int_User();
        try (   Connection conn = this.conn;
                PreparedStatement st = conn.prepareStatement(sqlState);
                ResultSet rs = st.executeQuery();
                ) {
            if (!rs.next()) Result.setInt(1);
            if (!pass.equals(rs.getString("U_pass"))) Result.setInt(2);
            if (rs.getString("Usertype").equals("1")) {
                Result.setInt(0);
                u = new User(rs.getString(1), rs.getString(2),
                        rs.getString(4),rs.getString(5),
                        rs.getString(6),rs.getString(7));
            }
            else Result.setInt(3);
            st.close();
            rs.close();
            //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Result.setUser(u);

        return Result;
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
                Connection conn = this.conn;
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
                    Connection conn = this.conn;
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

    public boolean ChangePassword (User user,String oldPass,String newPass){
        // if the inputted oldPass is incorrect or the new pass and old pass is the same
        System.out.println(user.pass + "  " + oldPass + "  " + newPass);
        if ((!user.pass.equals(oldPass))|| user.pass.equals(newPass)) return false;

        String sqlUpdate = "Update tblUser\n" +
                "set U_pass = " + "\'" + newPass + "\'" +
                "\nwhere Username = " + "\'" + user.username + "\'";
        try(
                Connection conn = this.conn;
                PreparedStatement sql = conn.prepareStatement(sqlUpdate);
                ){
            System.out.println(sqlUpdate);
            System.out.println(user.username);
            sql.executeUpdate();
            conn.commit();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean ChangeInformation (User user, String oldPass, User newInfo){
        // if the inputted oldPass is incorrect

        if (!user.pass.equals(oldPass) ) return false;

        String sqlUpdate = "Update tblUser\n" +
                "set Email = ?,\n" +
                "Phone = ?,\n" +
                "U_address = ?,\n" +
                "U_image = ?\n" +
                "where Username = ?";
        Connection conn = this.conn;
        try(
                PreparedStatement sql = conn.prepareStatement(sqlUpdate);
        ){

            sql.setString(1, newInfo.email );
            sql.setString(2, newInfo.phone );
            sql.setString(3,newInfo.addr );
            sql.setString(4, newInfo.image );
            sql.setString(5, user.username );
            System.out.println(sqlUpdate);
            sql.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void getNewConn() throws SQLException {
        this.conn = ConnectionPool.getConnection();
    }
}


