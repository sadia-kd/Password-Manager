package ui.gui;

import model.Account;
import model.PasswordManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class PasswordManagerGui extends JFrame implements ActionListener {
    private Account account;
    private PasswordManager passwordManager;

    private static final int WIDTH = 650;
    private static final int HEIGHT = 400;

    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel label;

    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenu menu2;

    private JLabel l1;
    private JLabel l2;
    private JLabel l3;

    private JTextField t1;
    private JTextField t2;
    private JTextField t3;

    private JTabbedPane tabbedPane;

    // TODO
    //
    public PasswordManagerGui() {
        passwordManager = new PasswordManager();

        // Create the window
        frame = new JFrame("Password Manager");
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 0));
        panel2.setBackground(Color.lightGray);

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0, 6));
        panel1.setBackground(Color.white);

        tabbedPane = new JTabbedPane();
        tabbedPane.add(panel1);
        tabbedPane.add(panel2);

        setThings();
        initialButtons();
        initalLabels();
        setMenu();
    }

    private void setThings() {
        frame.setSize(WIDTH, HEIGHT);
        //frame.add(panel1, BorderLayout.WEST);
        //frame.add(panel2, BorderLayout.EAST);
        frame.add(tabbedPane);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setMenu() {
        //Build menu bar.
        menuBar = new JMenuBar();
        menu1 = new JMenu("Load File");
        menu2 = new JMenu("Save File");

        menuBar.add(menu1);
        menu1.setMnemonic(KeyEvent.VK_A);
        //menu1.getAccessibleContext().setAccessibleDescription(
        //        "The only menu in this program that has menu items");

        menuBar.add(menu2);
        menu2.setMnemonic(KeyEvent.VK_A);
        //menu2.getAccessibleContext().setAccessibleDescription(
        //        "The only menu in this program that has menu items");

        frame.add(menuBar);
        frame.setJMenuBar(menuBar);

    }

    private void initialButtons() {
        button1 = new JButton("ADD");
        panel1.add(button1);
        button1.addActionListener(this);

        l1 = new JLabel("Application");
        l2 = new JLabel("Username");
        l3 = new JLabel("Password");

        t1 = new JTextField(10);
        t2 = new JTextField(10);
        t3 = new JTextField(10);

        frame.add(l1);
        frame.add(t1);
        frame.add(l2);
        frame.add(t2);
        frame.add(l3);
        frame.add(t3);

//
//        button2 = new JButton("REMOVE");
//        panel1.add(button2);
//
//        button3 = new JButton("VIEW");
//        panel1.add(button3);
//
//        button4 = new JButton("TOTAL");
//        button4.addActionListener(this);
//        panel1.add(button4);

    }

    private void initalLabels() {
        label = new JLabel("Saved Accounts");
        panel2.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == button1) {
            addPassword();
        } else if (object == button4) {
            total();
        }
    }

    private void addPassword() {
        String app = t1.getText();
        String u = t2.getText();
        String p = t2.getText();
        account = new Account(app, u, p);
        passwordManager.addAccount(account);
    }

    private void total() {
        JLabel label = new JLabel("The total passwords you have saved are: " + passwordManager.getCount());
        frame.add(label);

    }

}
