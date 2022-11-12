package ui;

import ui.gui.PasswordManagerGui;

import java.io.FileNotFoundException;


// Main is where the app will run from!
public class Main {
    public static void main(String[] args) {

        try {
            new PasswordManagerGui();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run Password Manager Application");
        }


//        try {
//            new PasswordManagerApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run Password Manager Application");
//        }


    }

}
