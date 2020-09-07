package services;

public interface AuthService {
    public boolean auth(String mailId, String password);

    public boolean isUserExist(String mailId);

    public boolean authValidate(String mailId, String password);

    public boolean signUp(String mailId, String password);
}