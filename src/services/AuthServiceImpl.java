package services;

import java.sql.*;

import db.MySqlConnector;
import model.UserModel;

public class AuthServiceImpl implements AuthService {

    @Override
    public boolean auth(String mailId, String password) {
        if (isUserExist(mailId)) {
            return authValidate(mailId, password);
        } else {
            return signUp(mailId, password);
        }
    }

    @Override
    public boolean isUserExist(String mailId) {
        try {
            Connection con = MySqlConnector.getConnection();
            String sql = "select count(*) as count from user_accounts where mailid=?";
            PreparedStatement preStmt = con.prepareStatement(sql);
            preStmt.setString(1, mailId);
            ResultSet result = preStmt.executeQuery();
            result.next();
            if (result.getInt(1) == 1) {
                System.out.println("User Exists");

                return true;
            } else {
                System.out.println("User Not Found");
            }
        } catch (Exception e) {
            System.out.println("isUserExist Exception " + e);
        }
        return false;
    }

    @Override
    public boolean authValidate(String mailId, String password) {
        try {
            Connection con = MySqlConnector.getConnection();
            String sql = "select count(*) as count from user_accounts where mailid=? and password=?";
            PreparedStatement preStmt = con.prepareStatement(sql);
            preStmt.setString(1, mailId);
            preStmt.setString(2, password);
            // System.out.println("Query " + preStmt.toString());
            ResultSet result = preStmt.executeQuery();
            result.next();
            if (result.getInt(1) == 1) {
                UserModel.setIsAuth(true);
                return true;
            } else {
                System.out.println("AuthValidation - invalid password ");
            }
        } catch (Exception e) {
            System.out.println("authValidate Exception " + e);
        }
        return false;
    }

    @Override
    public boolean signUp(String mailId, String password) {
        try {
            Connection con = MySqlConnector.getConnection();
            String userInsert = "insert into user_accounts values (?, ?)";
            PreparedStatement preSmt = con.prepareStatement(userInsert);
            preSmt.setString(1, mailId);
            preSmt.setString(2, password);
            // System.out.print("Signup Query : " + preSmt.toString());
            Integer result = preSmt.executeUpdate();
            System.out.println("user created successfully");
            UserModel.setIsAuth(true);
            return true;
        } catch (Exception e) {
            System.out.println("user signup failed with " + e);
        }
        return false;
    }
}