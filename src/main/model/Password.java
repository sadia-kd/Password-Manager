package model;

// Password represents one password that will be added to the list of passwords saved to Password Manager
public class Password {
    private char pass;              // represents the password of the application
    private char user;              // represents the username of the application
    private String application;     // represents the name of the application

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public Password(char p, char u, String app) {
        this.pass = p;
        this.user = u;
        this.application = app;
    }


    // getters for password, username, and application name
    public char getPass() {
        return pass;
    }

    public char getUser() {
        return user;
    }

    public String getApplication() {
        return application;
    }

}
