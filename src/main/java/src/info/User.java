package src.info;

public class User {
    private String userName = "";
    private int UID;
    private String Mail = "";

    public User(String userName, int UID) {
        this.userName = userName;
        this.UID = UID;
    }

    public User(int UID) {
        this.UID = UID;
    }

    public User() {

    }

    public User(String userName, int UID, String mail) {
        this.userName = userName;
        this.UID = UID;
        Mail = mail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public int getUID() {
        return UID;
    }

    public String getMail() {
        return Mail;
    }


}
