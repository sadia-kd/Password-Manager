package model;

// Password represents one password that will be added to the list of passwords saved to Password Manager
public class Password {
    private String pass;             // represents the password of the application that will be saved
    private String user;             // represents the username of the application that will be saved
    private String application;      // represents the name of the application that will be saved

    //TODO
    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: creates a single password with given password, corresponding username, and the application name
    public Password(String p, String u, String app) {
        this.pass = p;
        this.user = u;
        this.application = app;
    }

    // TODO
    // getters for password, username, and application name
    public String getPassword() {
        return pass;
    }

    public String getUsername() {
        return user;
    }

    public String getApplication() {
        return application;
    }


    public void accountDetails() {
        System.out.println("Application: " + getApplication() + "\n"
                + "Username: " + getUsername() + "\n"
                + "Password: " + getPassword());
    }

}
