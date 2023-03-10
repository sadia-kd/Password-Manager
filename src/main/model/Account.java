package model;

import org.json.JSONObject;
import persistence.Writable;

// Account represents one account info that will be saved to the Password Manager with its password, username
// and application name
public class Account implements Writable {
    private String application;      // represents the name of the application that will be saved
    private String user;             // represents the username of the application that will be saved
    private String pass;             // represents the password of the application that will be saved


    //EFFECTS: Constructor which creates a single account with the application name, given username,
    //         and the given password
    public Account(String app, String u, String p) {
        this.application = app;
        this.user = u;
        this.pass = p;
    }


    // Getters
    //EFFECTS: returns the application name of the account saved
    public String getApplicationName() {
        return application;
    }

    //EFFECTS: returns the Username of the account saved
    public String getUsername() {
        return user;
    }

    //EFFECTS: returns the Password of the account saved
    public String getPassword() {
        return pass;
    }


    // EFFECTS: returns as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("application", application);
        json.put("user", user);
        json.put("pass", pass);
        return json;
    }

}
