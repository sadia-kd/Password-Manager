package ui;

import model.Account;
import model.PasswordManager;

import java.util.Scanner;

// This will run the Password Manager console application
public class PasswordManagerApp {
    private PasswordManager manager;
    private Account account;
    private Scanner input;

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public PasswordManagerApp() {
        runPasswordManagerApp();
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void runPasswordManagerApp() {
        manager = new PasswordManager();
        input = new Scanner(System.in);
        System.out.println("\nHello! Welcome to your Password Manager!");

        boolean continueRunning = true;
        String command = null;

        while (continueRunning) {
            printOptions();
            command = input.next();

            if (command.equals("quit")) {
                continueRunning = false;
            } else {
                doNextOptions(command);
            }
        }
        System.out.println("Thank you for using your Password Manager!");
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: prints the operations available to perform on this password manager
    private void printOptions() {
        System.out.println("\nPlease select one of the options you would like to perform:");
        System.out.println("\tAdd a password: add");
        System.out.println("\tRemove a password: remove");
        System.out.println("\tView the accounts saved: view");
        System.out.println("\tTotal passwords saved: total");
        System.out.println("\tQuit the App: quit");
    }

    // TODO
    // REQUIRES
    // MODIFIES
    // EFFECTS
    private void doNextOptions(String command) {
        if (command.equals("add")) {
            addPassword();
        } else if (command.equals("remove")) {
            removePassword();
        } else if (command.equals("view")) {
            viewAccounts();
        } else if (command.equals("total")) {
            printTotal();
        } else {
            System.out.println("You did not enter one of the following options...");
        }
    }

    // TODO
    // REQUIRES
    // MODIFIES
    // EFFECTS
    private void addPassword() {
        account = new Account(null, null, null);
        System.out.println("Enter the name of the Application: ");
        String app = input.next();
        System.out.println("Enter the username: ");
        String u = input.next();
        System.out.println("Enter the password: ");
        String p = input.next();
        manager.addAccount(account);
        System.out.println("\nCongrats, this account has been saved to your Password Manager!!!");
    }

    // TODO
    // REQUIRES
    // MODIFIES
    // EFFECTS
    private void removePassword() {
        if (manager.count() == 0) {
            System.out.println("You do not have any passwords saved ... ");
        } else {
            System.out.println("What is the name of the application this password is for? ");
            String app = input.next();
            //manager.removeAccount(null);
            if (manager.removeAccount(null)) {
                System.out.println("This account has been removed!");
            } else {
                System.out.println("This account does not exist!");
            }
        }
    }

    // TODO
    // REQUIRES
    // MODIFIES
    // EFFECTS
    private void viewAccounts() {
        System.out.println("Application: " + account.getApplicationName() + "\n"
                + "Username: " + account.getUsername() + "\n"
                + "Password: " + account.getPassword());
    }

    // TODO
    // REQUIRES
    // MODIFIES
    // EFFECTS
    private void printTotal() {
        System.out.println("The total passwords you have saved are: " + manager.count());
    }
}
