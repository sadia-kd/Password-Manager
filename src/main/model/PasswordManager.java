package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// PasswordManager represents all the accounts that will be saved
public class PasswordManager implements Writable {
    private final List<Account> accounts;


    //EFFECTS: constructs an empty Array List that will hold all the accounts
    public PasswordManager() {
        accounts = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds an Account to accounts
    public void addAccount(Account account) {
        accounts.add(account);
    }

    //REQUIRES: must be given a non-empty string
    //EFFECTS: Checks to see if accounts contains an account with app as the application name and
    //         u as the username
    public boolean containsAccount(String app, String u) {
        for (Account a : accounts) {
            if ((Objects.equals(a.getApplicationName(), app)) && (Objects.equals(a.getUsername(), u))) {
                return true;
            }
        }
        return false;
    }

    // followed format from the Hockey project given
    //
    //REQUIRES: must be given a non-empty string
    //MODIFIES: this
    //EFFECTS: remove the account if there is an account saved with app as the application name and
    //         u as the username
    public boolean removeAccount(String app, String u) {
        for (Account a : accounts) {
            if ((Objects.equals(a.getApplicationName(), app)) && (Objects.equals(a.getUsername(), u))) {
                accounts.remove(a);
                return true;
            }
        }
        return false;
    }

    // getters

    //EFFECTS: returns the total count of accounts saved
    public int getCount() {
        return accounts.size();
    }

    //EFFECTS: returns the Account at index i
    public Account getAccount(int i) {
        return accounts.get(i);
    }




    //EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("accounts", accountsToJson());
        return json;
    }


    //EFFECTS: returns accounts in this password manager as a JSON array
    private JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Account a : accounts) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }



}
