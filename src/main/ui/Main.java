package ui;

import ui.gui.PasswordManagerGui;

import javax.swing.*;
import java.io.FileNotFoundException;


// Main is where the app will run from!
public class Main {
    public static void main(String[] args) {
        try {
            new PasswordManagerGui();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to run Password Manager Application",
                    "PROBLEM!", JOptionPane.WARNING_MESSAGE);
        }

    }

}
