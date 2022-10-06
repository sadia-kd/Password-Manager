package model;

import java.util.ArrayList;
import java.util.List;


// PasswordManager represents the passwords that will be saved
public class PasswordManager {
    private final List<Password> passwords;

    // TODO
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: constructs an empty Array List that will hold all the passwords
    public PasswordManager() {
        passwords = new ArrayList<>();
    }

    // TODO
    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void addPassword(Password password) {
        passwords.add(password);
    }

    // TODO
    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void removePassword(Password password) {
        passwords.remove(password);
    }

    // TODO
    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public int countPasswords() {
        return passwords.size();
    }



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
