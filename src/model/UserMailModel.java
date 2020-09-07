package model;

public class UserMailModel {
    private String mailId;
    private Integer id;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String content;

    public UserMailModel() {

    }

    public UserMailModel(String mailId, Integer id, String to, String cc, String bcc, String subject, String content) {
        this.mailId = mailId;
        this.id = id;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.content = content;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMailId() {
        return this.mailId;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTo() {
        return this.to;
    }

    public String getCc() {
        return this.cc;
    }

    public String getBcc() {
        return this.bcc;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getContent() {
        return this.content;
    }

}