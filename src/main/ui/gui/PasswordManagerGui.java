package ui.gui;

import model.Account;
import model.PasswordManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;


public class PasswordManagerGui extends JFrame implements ActionListener {
    private Account account;
    private PasswordManager passwordManager;

    private static final String JSON_STORE = "./data/PasswordManagerGUI.json";

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private JFrame frame;

    private JPanel panel;
    private JPanel panel2;

    private JTable table;
    private DefaultTableModel defaultTableModel;

    private JButton button1;
    private JButton button2;

    private JMenuBar menuBar;
    private JMenu menu;

    private JMenuItem item1;
    private JMenuItem item2;

    private JLabel l1;
    private JLabel l2;
    private JLabel l3;

    private JTextField t1;
    private JTextField t2;
    private JTextField t3;

    private JLabel l4;
    private JLabel l5;
    private JTextField t4;
    private JTextField t5;

    private JLabel countLabel;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Border br = BorderFactory.createLineBorder(Color.black);

    public PasswordManagerGui() throws FileNotFoundException {
        passwordManager = new PasswordManager();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();
        setUp();
        setMenu();
        addPanel();
        frame.setVisible(true);
    }

    private void setUp() {
        frame.setTitle("PASSWORD MANAGER");
        // TODO
        // what should i use
        //frame.setLayout(new GridLayout());
        frame.setLayout(null);
        frame.setBounds(0,0, WIDTH + 90, HEIGHT + 60);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }


    private void setMenu() {
        //Build menu bar.
        menuBar = new JMenuBar();
        menu = new JMenu("File");

        item1 = new JMenuItem("save");
        item1.addActionListener(this);
        item2 = new JMenuItem("load");
        item2.addActionListener(this);

        menu.add(item1);
        menu.add(item2);

        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        //menu.addActionListener(this);

        frame.add(menuBar);
        frame.setJMenuBar(menuBar);
    }


    private void addPanel() {
        //Creating a JPanel for the JFrame
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        //panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        // changing the background color of the panel
        panel.setBackground(Color.lightGray);
        panel.setBounds(10,10,(WIDTH / 5) * 3,HEIGHT - 20);
        setButtons();

        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBorder(br);
        panel2.setBackground(Color.GRAY);
        panel2.setBounds(320,10,(WIDTH / 5) * 3 - 50,HEIGHT / 8);

        //adding a label element to the panel
//        JLabel label = new JLabel("Accounts");
//        //label.setSize(90, 25);
//        //label.setBorder(BorderFactory.createLineBorder(Color.red));
//        label.setBounds(10,10,100,30);
//        panel2.add(label);

        countLabel = new JLabel("Total Accounts Saved: " + passwordManager.getCount());
        countLabel.setMinimumSize(new Dimension(100, 20));
        countLabel.setBounds(10,20,200,20);
        panel2.add(countLabel);

        //adding to the JFrame
        frame.add(panel);
        frame.add(panel2);
        setTable();
    }

//    private void updateSentence() {
//        countLabel = new JLabel("Total Accounts Saved: " + passwordManager.getCount());
//        countLabel.setMinimumSize(new Dimension(100, 20));
//        countLabel.setBounds(10,20,200,20);
//        panel2.add(countLabel);
//    }

    private void setButtons() {
        addEntries();
        button1 = new JButton("ADD");
        panel.add(button1);
        button1.addActionListener(this);


        button2 = new JButton("REMOVE");
        //removeEntries();
        panel.add(button2);
        button2.addActionListener(this);

    }

    private void setTable() {
        Object [] [] data = {

        };

        String[] columnNames = {"Application:",
                "User:",
                "Pass:"};

        defaultTableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(defaultTableModel);
        table.setBackground(Color.pink);
        table.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        table.setFillsViewportHeight(true);

        JScrollPane pane2 = new JScrollPane(table);
        pane2.setBounds(320,20 + HEIGHT / 8,(WIDTH / 5) * 3 - 50, HEIGHT / 2 + 160);
        frame.add(pane2);
    }


    private void addEntries() {
        l1 = new JLabel("Application: ");
        l1.setFont(new Font("Aerial", Font.BOLD + Font.ITALIC, 15));
        t1 = new JTextField(50);
        t1.setFont(new Font("Aerial", Font.BOLD, 20));

        l2 = new JLabel("Username: ");
        l2.setFont(new Font("Aerial", Font.BOLD + Font.ITALIC, 15));
        t2 = new JTextField(10);
        t2.setFont(new Font("Aerial", Font.BOLD, 20));

        l3 = new JLabel("Password: ");
        l3.setFont(new Font("Aerial", Font.BOLD + Font.ITALIC, 15));
        t3 = new JTextField(10);
        t3.setFont(new Font("Aerial", Font.BOLD, 20));

        setNull();

        panel.add(l1);
        panel.add(t1);
        panel.add(l2);
        panel.add(t2);
        panel.add(l3);
        panel.add(t3);
    }

    private void removeEntries() {
//        l4 = new JLabel("Application Name to remove: ");
//        t4 = new JTextField(10);
//        l5 = new JLabel("Username to remove: ");
//        t5 = new JTextField(10);
//        panel.add(l4);
//        panel.add(t4);
//        panel.add(l5);
//        panel.add(t5);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            addPassword();
        } else if (e.getSource() == button2) {
            removePassword();
        } else if (e.getSource() == item1) {
            savePasswordManager();
        } else if (e.getSource() == item2) {
            loadPasswordManager();
        }
    }


    private void addPassword() {
        String app = t1.getText();
        String u = t2.getText();
        String p = t3.getText();
        account = new Account(app, u, p);

        if (passwordManager.containsAccount(app, u)) {
            JOptionPane.showMessageDialog(null, "An account like this already exists!",
                    "Problem!", JOptionPane.INFORMATION_MESSAGE);
            setNull();
        } else {
            passwordManager.addAccount(account);
            setNull();
            System.out.println("\nThis account has been saved to your Password Manager!!!");
            Object[] newRow = {app, u, p};
            defaultTableModel.addRow(newRow);
        }
    }

    private void removePassword() {
        String app = t1.getText();
        String u = t2.getText();
        String p = t3.getText();
        if (passwordManager.getCount() == 0) {
            System.out.println("You do not have any passwords saved ... ");
            setNull();
            JOptionPane.showMessageDialog(null, "You have 0 passwords saved",
                    "Problem!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (passwordManager.removeAccount(app, u)) {

                Object[] row = {app, u, p};
                // TODO
                // fix
                //defaultTableModel.removeRow();
                defaultTableModel.addRow(row);

                System.out.println("\nThis account has been removed!");
                setNull();
            } else {
                System.out.println("\nThis Password Manager does not contain this account!");
                setNull();
                JOptionPane.showMessageDialog(null, "No such account exists",
                        "Problem!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void setNull() {
        t1.setText(null);
        t2.setText(null);
        t3.setText(null);
    }

    private void savePasswordManager() {
        try {
            jsonWriter.open();
            jsonWriter.write(passwordManager);
            jsonWriter.close();

            JOptionPane.showMessageDialog(null, "Saved!",
                    "Congrats!", JOptionPane.INFORMATION_MESSAGE);

        }  catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadPasswordManager() {
        try {
            passwordManager = jsonReader.read();
            System.out.println("Loaded Password Manager from " + JSON_STORE);

            JOptionPane.showMessageDialog(null, "Loaded!",
                    "Congrats!", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
