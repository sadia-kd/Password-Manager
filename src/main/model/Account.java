package model;

// Account represents one account info that will be saved to the Password Manager with its password, username
// and application name
public class Account {
    private String application;      // represents the name of the application that will be saved
    private String user;             // represents the username of the application that will be saved
    private String pass;             // represents the password of the application that will be saved

    //TODO
    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: creates a single account with given password, corresponding username, and the application name
    public Account(String app, String u,  String p) {
        this.application = app;
        this.user = u;
        this.pass = p;

    }

    // TODO
    // getters for password, username, and application name

    public String getApplicationName() {
        return application;
    }

    public String getUsername() {
        return user;
    }

    public String getPassword() {
        return pass;
    }


    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void changePassword(String app, String newPass) {
        if (app == application) {
            this.pass = newPass;
        }
    }

}
