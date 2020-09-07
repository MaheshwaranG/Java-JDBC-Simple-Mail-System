import db.MySqlConnector;
import java.sql.*;
import java.util.Scanner;

import services.AuthService;
import services.AuthServiceImpl;
import model.*;

import services.MailServices;
import services.MailServicesimpl;

class Mail {
    public static MySqlConnector connection;

    public static void main(String arg[]) {
        dbConnection();
        AuthService auth = new AuthServiceImpl();
        MailServices mailService = new MailServicesimpl();
        try {
            // if (auth.auth("test@gmail.co.in", "Test@123")) {
            // System.out.println(UserModel.getMailId() + " Found");
            // }
            // boolean create = connection.execute("insert into user_accounts
            // values('test@gmail.co.in', 'Test@123')");
            // ResultSet result = connection.executeQuery("select * from user_accounts");
            // while (result.next()) {
            // System.out.println(result.getString("mailId"));
            // }

            // mailService.getAllMails("test@gmail.co.in");

            // login();
            ActionsList.login();

        } catch (Exception e) {
            System.out.print("DB Server connection fail " + e);
        }

    }

    public static void dbConnection() {
        connection = new MySqlConnector();
    }

    public static void logincheck() {
        getCredentialsCheck();
        AuthService auth = new AuthServiceImpl();
        if (auth.auth(UserModel.getMailId(), UserModel.getPassword())) {
        }
    }

    public static void getCredentialsCheck() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Email Id : ");
        String mailid = in.nextLine();
        System.out.print("Enter Password : ");
        String password = in.nextLine();
        UserModel.createInstance(mailid, password, false);
    }
}