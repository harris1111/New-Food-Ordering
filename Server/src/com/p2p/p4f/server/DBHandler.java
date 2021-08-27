package com.p2p.p4f.server;

import com.p2p.p4f.protocols.*;
import com.p2p.p4f.protocols.InfoResponse;
import com.p2p.p4f.protocols.LoginInfo;
import com.p2p.p4f.protocols.RegisterInfo;
import com.p2p.p4f.protocols.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;



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
            if (!rs.first())
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
    public int Register(RegisterInfo u) throws SQLException {
        String sqlState = "insert into tblUser(Username, U_pass, Usertype, " +
                "Email, Phone, U_address, U_image) \nvalues";
        String email = u.getEmail();
        String phone = u.getPhone();
        String Usertype = "1";
        String username = u.getUsername();
        String addr = u.getAddress();
        String image = "NULL";
        String pass = u.getPassword();
        if (! email.equals("NULL"))  email = "\'" +  email + "\'";
        if (! phone.equals("NULL"))  phone = "\'" +  phone + "\'";
        String val = "(\'" +  username + "\'," +
                "\'" +  pass + "\',"+
                "\'" +  Usertype +"\'," +
                email + ","  +
                phone + "," +
                "N\'" +  addr +
                "\', " +  image + ");" ;
        String UserSt = "select * from tblUser where Username = ?";
        String EmailSt = "select * from tblUser where Email = ?";
        String PhoneSt = "select * from tblUser where Phone = ?";
        Statement st = conn.createStatement();
        PreparedStatement stUser = conn.prepareStatement(UserSt);
        PreparedStatement stEmail = conn.prepareStatement(EmailSt);
        PreparedStatement stPhone = conn.prepareStatement(PhoneSt);
        try {
            // Check Username
            stUser.setString(1, username);
            stEmail.setString(1,email);
            stPhone.setString(1,phone);
            ResultSet rs = stUser.executeQuery();
            if (rs.next()) return 1;
            // Check mail
            if (! email.equals("NULL")) {
                rs = stEmail.executeQuery();
                if (rs.next()) return 2;
            }
            // Check phone
            if (! phone.equals("NULL")) {
                rs = stPhone.executeQuery();
                if (rs.next()) return 3;
            }

            // Insert to DB
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

    private String getPass(String username) throws SQLException {
        PreparedStatement sql = conn.prepareStatement("select U_pass from tblUser where Username = ?");
        sql.setString(1, username);
        ResultSet rs = sql.executeQuery();
        if (rs.next()){
            return rs.getString("U_pass");
        }
        else return null;
    }
    // Change password
    public int ChangePassword (changePassInfo user) throws SQLException {
        String pass = getPass(user.getUsername());
        String oldPass = user.getOldPass();
        String newPass = user.getNewPass();
        // if get pass false, it means that user has some faults
        if (pass == null) return -1;
        if ((!pass.equals(oldPass))|| pass.equals(newPass)) return -1;
        // if the inputted oldPass is incorrect or the new pass and old pass is the same
        String Usertype = "1";
        String username =  user.getUsername();
        String sqlUpdate = "Update tblUser\n" +
                "set U_pass = " + "\'" + newPass + "\'" +
                "\nwhere Username = " + "\'" +  username + "\'";
        Connection conn = this.conn;
        PreparedStatement sql = conn.prepareStatement(sqlUpdate);
        try{
            System.out.println(sqlUpdate);
            System.out.println(username);
            sql.executeUpdate();
            conn.commit();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 1;
    }

    public boolean ChangeInformation (UserAccount user, String oldPass, UserAccount newInfo) throws SQLException {
        // if the inputted oldPass is incorrect
        String pass = getPass(user.getUsername());
        if ( pass == null || !pass.equals(oldPass) ) return false;

        String sqlUpdate = "Update tblUser\n" +
                "set Email = ?,\n" +
                "Phone = ?,\n" +
                "U_address = ?,\n" +
                "U_image = ?\n" +
                "where Username = ?";
        PreparedStatement sql = conn.prepareStatement(sqlUpdate);
        try{

            sql.setString(1, newInfo.getEmail());
            sql.setString(2, newInfo.getPhone() );
            sql.setString(3,newInfo.getAddress() );
            sql.setString(4, "NULL" );
            sql.setString(5, user.getUsername());
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

//    public boolean insertOrder ()
}
