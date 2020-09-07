
import java.util.*;

import model.UserModel;
import services.AuthService;
import services.AuthServiceImpl;
import services.MailServices;
import services.MailServicesimpl;

enum Actions {
    Back(0), View(1), Compose(2), Delete(3), Send(4), Skip(5);

    int id;

    Actions(int id) {
        this.id = id;
    }
}

public class ActionsList {
    static MailServices mailapi = new MailServicesimpl();

    public static void listMails() {
        mailapi.getAllMails(UserModel.getMailId());
        listActions();
    }

    public static void listActions() {
        System.out.println("Please Select any one :\n 1. View emails\t2. Compose mail\t3. Delete");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        switch (id) {
            case 1:
                viewMailById();
                break;
            case 2:
                composeMail();
                break;
            case 3:
                deleteMail();
                break;
            default:
                listActions();
        }
    }

    static public void viewMailById() {
        System.out.println("Please enter Id to view : ");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        mailapi.getMail(id);
        listMails();
    }

    static public void composeMail() {
        mailapi.sendMail();
        listMails();
    }

    static public void deleteMail() {
        System.out.println("Please enter Id to view : ");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        mailapi.deleteMail(id);
        listMails();
    }

    public static void login() {
        Scanner in = new Scanner(System.in);
        System.out.println("LogIn : \n User Email : ");
        String emailid = in.nextLine();
        System.out.println("Password : ");
        String password = in.nextLine();
        UserModel.createInstance(emailid, password, false);
        // UserModel.createInstance("test@gmail.co.in", "Test@123", false);
        AuthService authService = new AuthServiceImpl();
        if (authService.auth(UserModel.getMailId(), UserModel.getPassword())) {
            listMails();
        }
    }
}