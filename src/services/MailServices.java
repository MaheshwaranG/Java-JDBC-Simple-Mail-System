package services;

import model.UserMailModel;

public interface MailServices {
    public void getAllMails(String mailid);

    public void getMail(Integer id);

    public void sendMail();

    public void deleteMail(int id);
}