package ui;

import model.Account;
import model.PasswordManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the Password Manager console application
public class PasswordManagerApp {
    private static final String JSON_STORE = "./data/PasswordManager.json";
    private Account account;
    private PasswordManager manager;
    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs PasswordManager and runs the Password Manager Application
    public PasswordManagerApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPasswordManagerApp();
    }


    // followed format of TellerApp project to get user input
    //
    // MODIFIES: this
    // EFFECTS: starts with processing the user input
    private void runPasswordManagerApp() {
        manager = new PasswordManager();
        input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Hello! Welcome to your Password Manager!");

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
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Thank you for using your Password Manager!");
    }


    // EFFECTS: prints the operations available to perform on this Password Manager to the console
    private void printOptions() {
        System.out.println("\nPlease select one of the options you would like to perform:");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("\tAdd a password: add");
        System.out.println("\tRemove a password: remove");
        System.out.println("\tView the accounts saved: view");
        System.out.println("\tTotal passwords saved: total");
        System.out.println("\tSave Password Manager to file: save");
        System.out.println("\tLoad Password Manager from file: load");
        System.out.println("\tQuit the App: quit");
        System.out.println("-------------------------------------------------------------------");
    }


    // followed format of TellerApp for getting the user command input
    //
    // MODIFIES: this
    // EFFECTS: processes the command that the user inputs
    private void doNextOptions(String command) {
        if (command.equals("add")) {
            addPassword();
        } else if (command.equals("remove")) {
            removePassword();
        } else if (command.equals("view")) {
            viewAccounts();
        } else if (command.equals("total")) {
            printTotal();
        } else if (command.equals("save")) {
            savePasswordManager();
        } else if (command.equals("load")) {
            loadPasswordManager();
        } else {
            System.out.println("You did not enter one of the following options...");
        }
    }


    // MODIFIES: this
    // EFFECTS: Adds password only if it doesn't already contain an account for a same application with
    //          same username as this means that a similar account is already saved
    private void addPassword() {
        System.out.println("\nEnter the name of the Application: ");
        String app = input.next();
        System.out.println("Enter the username: ");
        String u = input.next();
        System.out.println("Enter the password: ");
        String p = input.next();
        account = new Account(app, u, p);
        if (manager.containsAccount(app, u)) {
            System.out.println("An account like this already exists in this Password Manager!");
        } else {
            manager.addAccount(account);
            System.out.println("\nThis account has been saved to your Password Manager!!!");
        }
    }


    // MODIFIES: this
    // EFFECTS: removes an account from the Password Manager
    private void removePassword() {
        if (manager.getCount() == 0) {
            System.out.println("You do not have any passwords saved ... ");
        } else {
            System.out.println("What is the name of the application this password is for? ");
            String app = input.next();
            System.out.println("What is the specific username for this application?");
            String u = input.next();
            if (manager.removeAccount(app, u)) {
                System.out.println("\nThis account has been removed!");
            } else {
                System.out.println("\nThis Password Manager does not contain this account!");
            }
        }
    }


    // EFFECTS: prints the specific details to view for all the accounts
    private void viewAccounts() {
        if (manager.getCount() == 0) {
            System.out.println("\nThere are no accounts to view!");
        } else {
            for (int i = 0; i < manager.getCount(); i++) {
                Account a = manager.getAccount(i);
                System.out.println("-----------------------------------------------"
                        + "\n" + "Application: " + a.getApplicationName() + "\n"
                        + "Username: " + a.getUsername() + "\n"
                        + "Password: " + a.getPassword() + "\n"
                        + "-----------------------------------------------");
            }
        }
    }


    // EFFECTS: prints the total # of accounts saved
    private void printTotal() {
        System.out.println("The total passwords you have saved are: " + manager.getCount());
    }


    // EFFECTS: saves the Password Manager to file
    public void savePasswordManager() {
        try {
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();
            System.out.println("Password Manager file has been saved to " + JSON_STORE);
        }  catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads the Password Manager from file
    public void loadPasswordManager() {
        try {
            manager = jsonReader.read();
            System.out.println("Loaded Password Manager from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
