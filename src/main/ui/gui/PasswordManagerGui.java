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
    private static final String JSON_STORE = "./data/PasswordManagerGUI.json";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private Account account;
    private PasswordManager passwordManager;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel defaultTableModel;

    private JButton addButton;
    private JButton removeButton;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem saveItem;
    private JMenuItem loadItem;

    private JLabel countLabel;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;

    private Border br = BorderFactory.createLineBorder(Color.black);

    public PasswordManagerGui() throws FileNotFoundException {
        passwordManager = new PasswordManager();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();
        setUp();
        setMenu();
        addPanels();
        frame.setVisible(true);
    }


    private void setUp() {
        frame.setTitle("PASSWORD MANAGER");
        frame.setLayout(null);
        frame.setBounds(0,0, WIDTH + 90, HEIGHT + 60);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }


    private void setMenu() {
        //Build menu bar.
        menuBar = new JMenuBar();
        menu = new JMenu("File");

        saveItem = new JMenuItem("save");
        saveItem.addActionListener(this);
        loadItem = new JMenuItem("load");
        loadItem.addActionListener(this);

        menu.add(saveItem);
        menu.add(loadItem);

        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        frame.add(menuBar);
        frame.setJMenuBar(menuBar);
    }


    private void addPanels() {
        //Creating a JPanel for the add/remove
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        //panel1.setLayout(new GridLayout());
        panel1.setBorder(br);
        panel1.setBackground(Color.lightGray);
        panel1.setBounds(20,20,(WIDTH / 5) * 2,HEIGHT / 5 * 4);
        setButtons();

        //Creating a JPanel for the label with count
        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBorder(br);
        panel2.setBackground(Color.GRAY);
        panel2.setBounds(20, 40 + HEIGHT / 5 * 4,(WIDTH / 5) * 2,HEIGHT / 12);

        //JLabel for the label with count
        countLabel = new JLabel();
        updateSentence();
        panel2.add(countLabel);

        //adding to the JFrame
        frame.add(panel1);
        frame.add(panel2);
        setTable();
    }

    private void updateSentence() {
        countLabel.setText("Total Accounts Saved:    " + passwordManager.getCount());
        countLabel.setFont(new Font("Serif", Font.BOLD, 13));
        countLabel.setBounds(20,10,200,20);
    }

    private void setButtons() {
        addEntries();

        addButton = new JButton("ADD");
        panel1.add(addButton);
        addButton.addActionListener(this);

        removeButton = new JButton("REMOVE");
        panel1.add(removeButton);
        removeButton.addActionListener(this);

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

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(240,20,(WIDTH / 5) * 3 + 20, HEIGHT - 40);
        frame.add(scrollPane);
    }


    private void clearTable() {
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < passwordManager.getCount(); i++) {
            Account account = passwordManager.getAccount(i);
            String app = account.getApplicationName();
            String u = account.getUsername();
            String p = account.getPassword();
            Object[] newRow = {app, u, p};
            defaultTableModel.addRow(newRow);
        }
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

        panel1.add(l1);
        panel1.add(t1);
        panel1.add(l2);
        panel1.add(t2);
        panel1.add(l3);
        panel1.add(t3);

        setNull();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addPassword();
        } else if (e.getSource() == removeButton) {
            removePassword();
        } else if (e.getSource() == saveItem) {
            savePasswordManager();
        } else if (e.getSource() == loadItem) {
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
            Object[] newRow = {app, u, p};
            defaultTableModel.addRow(newRow);

            JOptionPane.showMessageDialog(null, "Account added!",
                    "Congrats!", JOptionPane.INFORMATION_MESSAGE);

            updateSentence();
        }
    }

    private void removePassword() {
        String app = t1.getText();
        String u = t2.getText();
        String p = t3.getText();
        if (passwordManager.getCount() == 0) {
            setNull();
            JOptionPane.showMessageDialog(null, "You have 0 passwords saved",
                    "Problem!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (passwordManager.removeAccount(app, u)) {
                setNull();
                clearTable();
                JOptionPane.showMessageDialog(null, "Account removed!",
                        "Congrats!", JOptionPane.INFORMATION_MESSAGE);
                updateSentence();
            } else {
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
        if (passwordManager.getCount() == 0) {
            JOptionPane.showMessageDialog(null, "You have not added any passwords to save!",
                    "Problem!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                jsonWriter.open();
                jsonWriter.write(passwordManager);
                jsonWriter.close();

                JOptionPane.showMessageDialog(null, "Saved!",
                        "Congrats!", JOptionPane.INFORMATION_MESSAGE);

            }  catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE,
                        "Problem!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void loadPasswordManager() {
        try {
            passwordManager = jsonReader.read();
            clearTable();
            updateSentence();
            JOptionPane.showMessageDialog(null, "Loaded Password Manager from " + JSON_STORE,
                    "Congrats!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE,
                    "Problem!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
