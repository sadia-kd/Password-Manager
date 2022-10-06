package ui;

import model.Password;
import model.PasswordManager;

import java.util.Scanner;

// This will run the Password Manager console application
public class PasswordManagerApp {
    private PasswordManager manager;
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
        System.out.println("\tTotal passwords saved: total");
        System.out.println("\tQuit the App: quit");
    }

    // TODO
    // REQUIRES
    // MODIFIES
    // EFFECTS
    private void doNextOptions(String command) {
        if (command.equals("total")) {
            printTotal();
        } else {
            System.out.println("You did not enter one of the following options...");
        }
    }

//    // TODO
//    // REQUIRES
//    // MODIFIES
//    // EFFECTS
//    private void addPassword() {
//        Password password = new Password();
//        System.out.println("Enter the Application you want to save the password for: ");
//        pass = input.next();
//    }

    // TODO
    // REQUIRES
    // MODIFIES
    // EFFECTS
    private void printTotal() {
        System.out.println("The total passwords you have saved are: " + manager.countPasswords());
    }
}
