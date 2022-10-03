package model;

import java.util.ArrayList;

// PasswordManager represents the passwords that have been added
public class PasswordManager {
    private final ArrayList<Password> passwords;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public PasswordManager() {
        passwords = new ArrayList<>();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void addPassword(Password password) {
        passwords.add(password);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void removePassword(Password password) {
        passwords.remove(password);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    //public int countPasswords(ArrayList<Password>) {
    //    passwords.size();
    //}

}
