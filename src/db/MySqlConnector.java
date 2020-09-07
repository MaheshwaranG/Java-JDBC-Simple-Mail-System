package db;

import java.sql.*;

public class MySqlConnector {
    private static Connection con;
    private Statement stmt;

    public MySqlConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:8085/simplemail", "admin", "love");
        } catch (Exception e) {
            System.out.println("Connection failed");
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public Statement getStmt() throws Exception {
        stmt = con.createStatement();
        return stmt;
    }

    public ResultSet executeQuery(String query) throws Exception {
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        System.out.println("Statement");
        return rs;
    }

    public boolean execute(String query) throws Exception {
        Statement statement = con.createStatement();
        boolean rs = statement.execute(query);
        System.out.println("Statement");
        return rs;
    }
}