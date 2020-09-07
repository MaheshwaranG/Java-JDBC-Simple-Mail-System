package services;

import java.util.*;
import java.sql.*;
import db.MySqlConnector;
import model.UserMailModel;
import model.UserModel;
import javax.mail.*;
import javax.mail.internet.*;

public class MailServicesimpl implements MailServices {

    public List<UserMailModel> mailList = new ArrayList<>();

    @Override
    public void getAllMails(String mailId) {
        try {
            Connection con = MySqlConnector.getConnection();
            String sql = "select mailid, id, to_add, cc_add, bcc_add, subject, content from mails where mailid=?";
            PreparedStatement preStmt = con.prepareStatement(sql);
            preStmt.setString(1, mailId);
            ResultSet result = preStmt.executeQuery();
            mailList.clear();
            while (result.next()) {
                UserMailModel mailModel = new UserMailModel();
                mailModel.setId(result.getInt("id"));
                mailModel.setMailId(result.getString("mailid"));
                mailModel.setTo(result.getString("to_add"));
                mailModel.setCc(result.getString("cc_add"));
                mailModel.setBcc(result.getString("bcc_add"));
                mailModel.setSubject(result.getString("subject"));
                mailModel.setContent(result.getString("content"));
                mailList.add(mailModel);
                // System.out.println(mailModel.getId() + "\t\t" + mailModel.getTo() + "\t\t"
                // + mailModel.getSubject().substring(0, 10));
            }
            listMail();

        } catch (Exception e) {
            System.out.println("View Mail List Exception " + e);
        }
    }

    public void mailView(UserMailModel mailModel) {
        {
            // UserMailModel mailModel = mailList.get(index);
            System.out.println(mailModel.getId() + "\t\t" + mailModel.getTo() + "\t\t" + mailModel.getSubject());
        }
    }

    public void listMail() {
        System.out.println("------------------------------------------------------------");
        System.out.println("ID\t\t TO \t\t Subject");
        // IntStream.range(0, mailList.size()).forEach(index -> {
        // UserMailModel mailModel = mailList.get(index);
        // System.out.println(index + "\t\t" + mailModel.getTo() + "\t\t" +
        // mailModel.getSubject());
        // });
        MailServicesimpl impl = new MailServicesimpl();
        mailList.stream().forEach(impl::mailView);
        System.out.println("============================================================");

    }

    @Override
    public void getMail(Integer index) {

        mailList.stream().filter(mail -> {
            return mail.getId() == index;
        }).forEach(mailModel -> {
            System.out.println("------------------------------------------------------------");
            System.out.println(mailModel.getSubject());
            System.out.println("To : " + mailModel.getTo());
            System.out.println();
            System.out.println("Subject : " + mailModel.getSubject());
            System.out.println();
            System.out.println(mailModel.getContent());
            System.out.println("------------------------------------------------------------");
        });
    }

    @Override
    public void sendMail() {
        Scanner in = new Scanner(System.in);
        UserMailModel mailModel = new UserMailModel();
        System.out.println("Please Enter To Address : ");
        mailModel.setMailId(UserModel.getMailId());
        mailModel.setTo(in.nextLine());
        System.out.println("Mail Subject : ");
        mailModel.setSubject(in.nextLine());
        System.out.println("Mail Content : ");
        mailModel.setContent(in.nextLine());
        // sendEmail(mailModel);
        insertMail(mailModel);

    }

    private void sendEmail(UserMailModel mailModel) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        // get Session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(UserModel.getMailId(), UserModel.getPassword());
            }
        });
        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailModel.getTo()));
            message.setSubject(mailModel.getSubject());
            message.setText(mailModel.getContent());
            // send message
            Transport.send(message);
            System.out.println("message sent successfully");
            insertMail(mailModel);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private void insertMail(UserMailModel mailModel) {
        try {
            Connection con = MySqlConnector.getConnection();
            String sql = "insert into mails (mailid, to_add, subject, content) values (?, ? , ? ,?)";
            PreparedStatement preStmt = con.prepareStatement(sql);
            preStmt.setString(1, mailModel.getMailId());
            preStmt.setString(2, mailModel.getTo());
            preStmt.setString(3, mailModel.getSubject());
            preStmt.setString(4, mailModel.getContent());
            boolean result = preStmt.execute();
            if (result) {
                mailList.add(mailModel);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void deleteMail(int id) {
        try {

            Connection con = MySqlConnector.getConnection();
            String q = "delete from mails where id=?";
            PreparedStatement preStm = con.prepareStatement(q);
            mailList.stream().filter(mail -> {
                return mail.getId() == id;
            }).forEach((mailModel) -> {
                try {
                    preStm.setInt(1, mailModel.getId());
                    preStm.execute();
                } catch (Exception e) {
                    System.out.println("Deleting mail issue - 1 : " + e);
                }
            });
        } catch (Exception e) {
            System.out.println("Deleting mail issue - 2 : " + e);
        }

    }

}