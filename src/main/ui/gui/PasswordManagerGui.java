package ui.gui;

import model.Account;
import model.PasswordManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the Password Manager GUI
public class PasswordManagerGui implements ActionListener {
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
    private JLabel l4;
    private JLabel l5;
    private JTextField t4;
    private JTextField t5;

    private JPanel addPanel;
    private JPanel removePanel;

    private ImageIcon imageIcon;
    private JLabel imageLabel;


    //EFFECTS: Constructs the PasswordManage GUI application
    public PasswordManagerGui() throws FileNotFoundException {
        passwordManager = new PasswordManager();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setUp();
        setBackground();
        setMenu();
        addPanels();
        frame.setVisible(true);
    }

    /*
    Followed this to see how to start with a JFrame.
     https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing
     /examples/components/FrameDemoProject/src/components/FrameDemo.java
    */
    //EFFECTS: Sets up the JFrame with the following characteristics
    private void setUp() {
        frame = new JFrame();
        frame.setTitle("PASSWORD MANAGER");
        frame.setLayout(null);
        frame.setBounds(0, 0, WIDTH + 90, HEIGHT + 60);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }


    // Followed the idea in this to set a background picture to half the JFrame
    // https://java-demos.blogspot.com/2012/09/setting-background-image-in-jframe.html
    //
    //EFFECTS: Adds the visual component to the left side of the frame
    private void setBackground() {
        imageIcon = new ImageIcon("./data/PasswordManager.jpg");
        imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(10, 5, 240, HEIGHT - 10);
        frame.add(imageLabel);
    }


    /*
    Followed this to set my save and load menu items
    https://docs.oracle.com/javase/tutorial/uiswing/examples/components/MenuDemoProject/src/components/MenuDemo.java
     */
    //EFFECTS: Sets the menu and adds it to the JFrame
    private void setMenu() {
        // Build menu bar.
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        // Construct menu items
        saveItem = new JMenuItem("save");
        saveItem.addActionListener(this);
        loadItem = new JMenuItem("load");
        loadItem.addActionListener(this);
        // Add menu items to menu
        menu.add(saveItem);
        menu.add(loadItem);
        menu.setMnemonic(KeyEvent.VK_A);
        // Add menu to menu bar
        menuBar.add(menu);
        // Add menu bar to JFrame
        frame.add(menuBar);
        frame.setJMenuBar(menuBar);
    }

    /*
    Adding panels:
    https://stackoverflow.com/questions/70630664/putting-panels-in-a-big-panel
    Setting Panel to a transparent colour:
    https://java-demos.blogspot.com/2013/09/creating-transparent-jpanel-in-swing.html
     */
    //EFFECTS: Adds the panels to the JFrame image background
    private void addPanels() {
        // JPanel for the add/remove labels and text fields
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        panel1.setBackground(new Color(0, 0, 0, 15));
        panel1.setBounds(20, 35, (WIDTH / 5) * 2, HEIGHT / 5 * 3 + 90);
        // Add Buttons to the panel1
        setButtons();

        // JPanel for the label with count
        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        panel2.setBackground(Color.lightGray);
        panel2.setBounds(55,HEIGHT - 52, (WIDTH / 5) * 2 - 55, HEIGHT / 15);
        // JLabel for the label with count
        countLabel = new JLabel();
        updateSentence();
        panel2.add(countLabel);

        // Adding both panels to the image background
        imageLabel.add(panel1);
        imageLabel.add(panel2);

        setTable();
    }


    //MODIFIES: this
    //EFFECTS: updates the label whenever account is added/removed/loaded from file.
    private void updateSentence() {
        countLabel.setText("Total Accounts Saved: " + passwordManager.getCount());
        countLabel.setFont(new Font("Serif", Font.BOLD, 12));
        countLabel.setBounds(10, 10, 200, 10);
    }


    /*
    Looked at to see how buttons can be added to a JFrame:
    https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java
    */
    //EFFECTS: adds the ADD and REMOVE panels to panel1 with the buttons
    private void setButtons() {
        // add l1-l3, t1-t3
        addEntries();
        // add the ADD button panel
        addPanel = new JPanel();
        addPanel.setBackground(Color.lightGray);
        panel1.add(Box.createRigidArea(new Dimension(0, 30)));
        panel1.add(addPanel);
        // add l4-l5, t4-t5
        removeEntries();
        // add the REMOVE button panel
        removePanel = new JPanel();
        removePanel.setBackground(Color.lightGray);
        panel1.add(Box.createRigidArea(new Dimension(0, 30)));
        panel1.add(removePanel);

        // add 'ADD' button to its panel
        addButton = new JButton("ADD");
        addPanel.add(addButton);
        // add 'REMOVE' button to its panel
        removeButton = new JButton("REMOVE");
        removePanel.add(removeButton);

        // ActionListener added to both buttons
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
    }


    /*
    Followed this to make Table with all accounts’ details:
    https://docs.oracle.com/javase/tutorial/uiswing/examples/components/SimpleTableSelectionDemoProject/src/
    components/SimpleTableSelectionDemo.java
     */
    //EFFECTS: Creates the table that will show the Accounts details and is added to JFrame
    private void setTable() {
        Object[][] data = {

        };

        String[] columnNames = {"Application:",
                "User:",
                "Pass:"};

        defaultTableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(defaultTableModel);
        table.setBackground(Color.pink);
        table.setFillsViewportHeight(true);

        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(260, 5, (WIDTH / 5) * 3 + 5, HEIGHT - 10);
        frame.add(scrollPane);
    }


    //MODIFIES: this
    //EFFECTS: Clears the table to 0 and re-adds the Accounts details from the passwordManager
    private void clearAndReloadTable() {
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


    /*
    Labels:
    https://docs.oracle.com/javase/tutorial/uiswing/examples/components/LabelDemoProject/src/components/LabelDemo.java
    TextFields:
    https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextFieldDemoProject/src/
    components/TextFieldDemo.java
    */
    //
    //EFFECTS: Adds the ADD Labels and TextFields to panel1
    private void addEntries() {
        l1 = new JLabel("Application: ");
        l1.setFont(new Font("Aerial", Font.BOLD + Font.ITALIC, 12));
        t1 = new JTextField(13);
        t1.setFont(new Font("Aerial", Font.BOLD, 12));

        l2 = new JLabel("Username:  ");
        l2.setFont(new Font("Aerial", Font.BOLD + Font.ITALIC, 12));
        t2 = new JTextField(13);
        t2.setFont(new Font("Aerial", Font.BOLD, 12));

        l3 = new JLabel("Password: ");
        l3.setFont(new Font("Aerial", Font.BOLD + Font.ITALIC, 12));
        t3 = new JTextField(13);
        t3.setFont(new Font("Aerial", Font.BOLD, 12));

        panel1.add(l1);
        panel1.add(t1);
        panel1.add(l2);
        panel1.add(t2);
        panel1.add(l3);
        panel1.add(t3);

        setNullAdd();
    }


    /*
    Labels:
    https://docs.oracle.com/javase/tutorial/uiswing/examples/components/LabelDemoProject/src/components/LabelDemo.java
    TextFields:
    https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextFieldDemoProject/src/
    components/TextFieldDemo.java
    */
    //
    //EFFECTS: Adds the REMOVE Labels and TextFields to panel1
    private void removeEntries() {
        l4 = new JLabel("Application to remove: ");
        l4.setFont(new Font("Aerial", Font.BOLD + Font.ITALIC, 12));
        t4 = new JTextField(13);
        t4.setFont(new Font("Aerial", Font.BOLD, 10));

        l5 = new JLabel("Username to remove: ");
        l5.setFont(new Font("Aerial", Font.BOLD + Font.ITALIC, 12));
        t5 = new JTextField(13);
        t5.setFont(new Font("Aerial", Font.BOLD, 10));

        panel1.add(l4);
        panel1.add(Box.createRigidArea(new Dimension(0, 30)));
        panel1.add(t4);
        panel1.add(Box.createRigidArea(new Dimension(0, 30)));
        panel1.add(l5);
        panel1.add(t5);

        setNullRemove();
    }


    //EFFECTS: sets the ADD text fields to null
    private void setNullAdd() {
        t1.setText(null);
        t2.setText(null);
        t3.setText(null);
    }

    //EFFECTS: sets the REMOVE text fields to null
    private void setNullRemove() {
        t4.setText(null);
        t5.setText(null);
    }


    // Followed to get an idea of how to make buttons work with Action Listener
    // https://docs.oracle.com/javase/tutorial/uiswing/events/intro.html
    // https://stackoverflow.com/questions/5936261/how-to-add-action-listener-that-listens-to-multiple-buttons
    //
    @Override
    // MODIFIES: this
    // EFFECTS: processes the button/menu that the user clicks
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


    /*
    For Pop-Up Dialog Messages:
    https://docs.oracle.com/javase/tutorial/uiswing/examples/components/
    DialogDemoProject/src/components/CustomDialog.java

    - Used in addPassword(), removePassword(), savePasswordManager(), and loadPasswordManager()
     */

    // MODIFIES: this
    // EFFECTS: Gets texts from textFields and adds password only if it doesn't already contain an account for a
    //          same application with same username as this means that a similar account is already saved
    //          Also, updates the table if the account is added
    private void addPassword() {
        String app = t1.getText();
        String u = t2.getText();
        String p = t3.getText();

        account = new Account(app, u, p);

        if (app.isEmpty() || u.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid string!",
                    "Problem!", JOptionPane.WARNING_MESSAGE);
            setNullAdd();
        } else if (passwordManager.containsAccount(app, u)) {
            JOptionPane.showMessageDialog(null, "An account like this already exists!",
                    "Problem!", JOptionPane.WARNING_MESSAGE);
            setNullAdd();
        } else {
            passwordManager.addAccount(account);
            setNullAdd();
            Object[] newRow = {app, u, p};
            defaultTableModel.addRow(newRow);
            updateSentence();

            JOptionPane.showMessageDialog(null, "Account added!",
                    "Congrats!", JOptionPane.INFORMATION_MESSAGE);

        }
    }


    // MODIFIES: this
    // EFFECTS: removes an account from the Password Manager and from the table
    private void removePassword() {
        String app = t4.getText();
        String u = t5.getText();

        if (app.isEmpty() || u.isEmpty()) {
            setNullRemove();
            JOptionPane.showMessageDialog(null, "Please enter a valid string!",
                    "Problem!", JOptionPane.WARNING_MESSAGE);
        } else if (passwordManager.getCount() == 0) {
            setNullRemove();
            JOptionPane.showMessageDialog(null, "You have 0 passwords saved",
                    "Problem!", JOptionPane.WARNING_MESSAGE);
        } else {
            if (passwordManager.removeAccount(app, u)) {
                setNullRemove();
                clearAndReloadTable();
                updateSentence();
                JOptionPane.showMessageDialog(null, "Account removed!",
                        "Congrats!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                setNullRemove();
                JOptionPane.showMessageDialog(null, "No such account exists",
                        "Problem!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


    //EFFECTS: saves the Password Manager to file,
    //         or throws FileNotFoundException if unable to save.
    private void savePasswordManager() {
        try {
            jsonWriter.open();
            jsonWriter.write(passwordManager);
            jsonWriter.close();

            JOptionPane.showMessageDialog(null, "Saved Password Manager to: " + JSON_STORE,
                    "Congrats!", JOptionPane.INFORMATION_MESSAGE);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE,
                    "Problem!", JOptionPane.WARNING_MESSAGE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads the Password Manager from file, loads items onto table, updates count,
    //          or throws FileNotFoundException if unable to read file
    private void loadPasswordManager() {
        try {
            passwordManager = jsonReader.read();
            clearAndReloadTable();
            updateSentence();
            JOptionPane.showMessageDialog(null, "Loaded Password Manager from " + JSON_STORE,
                    "Congrats!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE,
                    "Problem!", JOptionPane.WARNING_MESSAGE);
        }
    }

}
