package com.p2p.p4f.server;

import com.p2p.p4f.protocols.InfoResponse;
import com.p2p.p4f.protocols.LoginInfo;
import com.p2p.p4f.protocols.UserAccount;

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
    // Constructor to get conn by the parameter Connection conn
    public DBHandler(Connection conn){
        this.conn = conn;
    }
    // Constructor to get conn from pool if there is a idle connection in pool, if not, we have to wait
    public DBHandler() throws SQLException {
        this.conn = ConnectionPool.getConnection();
    }

    // Int_User: int to get the result of query, 0(no fault) 1(username fault) 2(pass fault) 3(staff login)
    // User: get information of the login user
    public InfoResponse Login(LoginInfo user) throws SQLException {
        String sqlState = "select * from tblUser u where u.Username = \'" + user.getUsername() + "\'";
        InfoResponse.Builder Result = InfoResponse.newBuilder();
        UserAccount.Builder account = UserAccount.newBuilder();
        PreparedStatement st = conn.prepareStatement(sqlState);
        ResultSet rs = st.executeQuery();
        try{
            // if username is invalid
            if (!rs.next())
                Result.setReCode(1);
                // if password is incorrect
            else if (!user.getPassword().equals(rs.getString("U_pass")))
                Result.setReCode(2);
                // customer login
            else if (rs.getString("Usertype").equals("1")) {
                Result.setReCode(0);
                // set username
                account.setUsername(rs.getString(1));
                // set user type: 1 is customer
                account.setType(1);
                // set email
                if (rs.getString(4) == null) account.setEmail("null");
                else account.setEmail(rs.getString(4));
                // set phone
                if (rs.getString(5) == null) account.setPhone("null");
                else account.setPhone(rs.getString(5));
                // set address
                if (rs.getString(6) == null) account.setAddress("null");
                else account.setAddress(rs.getString(6));
                Result.setUserInfo(account);
            }
            // staff login
            else {
                Result.setReCode(3); // NEED TO GET INFO OF STAFFS
                // set username
                account.setUsername(rs.getString(1));
                // set user type: 1 is customer
                account.setType(0);
                // set email
                if (rs.getString(4) == null) account.setEmail("NULL");
                else account.setEmail(rs.getString(4));
                // set phone
                if (rs.getString(5) == null) account.setPhone("NULL");
                else account.setPhone(rs.getString(5));
                // set address
                if (rs.getString(6) == null) account.setAddress("NULL");
                else account.setAddress(rs.getString(6));
                Result.setUserInfo(account);
            }
            st.close();
            rs.close();
            //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result.build();
    }

    // 0 = no fault, 1 = user fault, 2 = email fault, 3 = phone fault
    public int Register(User u) throws SQLException {
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
        //PreparedStatement st = conn.prepareStatement(sqlState+val);
        Statement st = conn.createStatement();
        PreparedStatement stQuery = conn.prepareStatement(QState);
        try {
            stQuery.setString(1, "u.Username");
            stQuery.setString(2, "\'" + u.username + "\'");
            ResultSet rs = stQuery.executeQuery();
            if (rs.next()) return 1;
            stQuery.setString(1, "u.Email");
            stQuery.setString(2, "\'" + u.email + "\'");
            if (!u.email.equals("NULL")) {
                rs = stQuery.executeQuery();
                if (rs.next()) return 2;
            }
            stQuery.setString(1, "u.Phone");
            stQuery.setString(2, "\'" + u.phone + "\'");
            if (!u.phone.equals("NULL")) {
                rs = stQuery.executeQuery();
                if (rs.next()) return 3;
            }
            st.executeUpdate(sqlState + val);
            return 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 5;
    }

    // Restaurant: Branch_ID, Branch_Name, Branch_Address, Branch_image, Branch_Location_Longtitude, Branch_Location_Latitude
    // example: rs.getArray("BranchID") is get column BranchID.
    public ArrayList<Restaurant> get_ListRestaurant() throws SQLException {
            String sqlSelect = "SELECT * from tblBranch";
            ArrayList<Restaurant> list = new ArrayList<>();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlSelect);
            try{
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Change password
    public boolean ChangePassword (User user,String oldPass,String newPass) throws SQLException {
        // if the inputted oldPass is incorrect or the new pass and old pass is the same
        System.out.println(user.pass + "  " + oldPass + "  " + newPass);
        if ((!user.pass.equals(oldPass))|| user.pass.equals(newPass)) return false;

        String sqlUpdate = "Update tblUser\n" +
                "set U_pass = " + "\'" + newPass + "\'" +
                "\nwhere Username = " + "\'" + user.username + "\'";
        Connection conn = this.conn;
        PreparedStatement sql = conn.prepareStatement(sqlUpdate);
        try{
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

    public boolean ChangeInformation (User user, String oldPass, User newInfo) throws SQLException {
        // if the inputted oldPass is incorrect

        if (!user.pass.equals(oldPass) ) return false;

        String sqlUpdate = "Update tblUser\n" +
                "set Email = ?,\n" +
                "Phone = ?,\n" +
                "U_address = ?,\n" +
                "U_image = ?\n" +
                "where Username = ?";
         PreparedStatement sql = conn.prepareStatement(sqlUpdate);
        try{

            sql.setString(1, newInfo.email );
            sql.setString(2, newInfo.phone );
            sql.setString(3,newInfo.addr );
            sql.setString(4, newInfo.image );
            sql.setString(5, user.username );
            sql.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // if the connection is not returned to pool, use this func to do it
    public void releaseConn() throws SQLException {
        if (!conn.isClosed()){
            conn.close();
        }
    }

}


