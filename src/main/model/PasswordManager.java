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
    //MODIFIES:
    //EFFECTS:
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // TODO
    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public boolean removeAccount(String app) {
        for (Account a: accounts) {
            if (Objects.equals(a.getApplicationName(), app)) {
                accounts.remove(a);
                return true;
            }
        }
        return false;
    }


    // TODO
    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public int count() {
        return accounts.size();
    }

    public Account getAccount(int i) {
        return accounts.get(i);
    }

//    public list<Account> returnAccounts() {
//        return accounts;
//    }

//    public void printAppNames() {
//        List<Password> appList = password.get();
//    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    //public void changePassword(String p) {
    //    if
    //}


}
