package model;

public class UserModel {
    private static String mailId;
    private static String password;
    private static boolean isAuth;

    private UserModel(String mailId, String password) {
        UserModel.mailId = mailId;
        UserModel.password = password;
    }

    public static void createInstance(String mailId, String password, boolean isAuth) {
        UserModel.mailId = mailId;
        UserModel.password = password;
        UserModel.isAuth = false;
    }

    public static void setMailId(String mailId) {
        UserModel.mailId = mailId;
    }

    public static void setPassword(String password) {
        UserModel.password = password;
    }

    public static void setIsAuth(boolean isAuth) {
        UserModel.isAuth = isAuth;
    }

    public static String getMailId() {
        return UserModel.mailId;
    }

    public static String getPassword() {
        return UserModel.password;
    }

    public static boolean getIsAuth() {
        return UserModel.isAuth;
    }

}