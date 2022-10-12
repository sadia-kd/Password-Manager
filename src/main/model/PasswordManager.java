package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// PasswordManager represents the accounts that will be saved
public class PasswordManager {
    private final List<Account> accounts;

    // TODO
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: constructs an empty Array List that will hold all the passwords/usernames/app names of accounts
    public PasswordManager() {
        accounts = new ArrayList<>();
    }


    // TODO
    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: adds and Account to accounts
    public void addAccount(Account account) {
        accounts.add(account);
    }


    // TODO
    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public boolean containsAccount(String app, String u) {
        for (Account a: accounts) {
            if (Objects.equals(a.getApplicationName(), app) && Objects.equals(a.getUsername(), u)) {
                return true;
            }
        }
        return false;
    }


    // TODO
    // followed format from Hockey project
    //REQUIRES:
    //MODIFIES: this
    //EFFECTS:
    public boolean removeAccount(String app, String u) {
        for (Account a: accounts) {
            if (Objects.equals(a.getApplicationName(), app) && Objects.equals(a.getUsername(), u)) {
                accounts.remove(a);
                return true;
            }
        }
        return false;
    }


    // getter
    // TODO
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the total count of accounts saved
    public int getCount() {
        return accounts.size();
    }


    // TODO
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the Account at index i
    public Account getAccount(int i) {
        return accounts.get(i);
    }


}
