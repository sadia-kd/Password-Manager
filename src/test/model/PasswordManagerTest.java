package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordManagerTest {
    private PasswordManager passwordManagerEmpty;
    private Account account1;
    private Account account2;
    private Account account3;
    private PasswordManager passwordManagerFull;

    @BeforeEach
    public void setUp() {
        this.passwordManagerEmpty = new PasswordManager();
        this.account1 = new Account("IG", "sadia", "cpsc");
        this.account2 = new Account("ssc", "skd", "210");
        this.account3 = new Account("SC", "sd", "cpsc210");
        this.passwordManagerFull = new PasswordManager();
        passwordManagerFull.addAccount(account1);
        passwordManagerFull.addAccount(account2);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, passwordManagerEmpty.count());
    }

    @Test
    public void testAddAccount() {
        passwordManagerEmpty.addAccount(account1);
        assertEquals(1, passwordManagerEmpty.count());
    }

    @Test
    public void testAddAccountMultiple() {
        assertEquals(0, passwordManagerEmpty.count());
        passwordManagerEmpty.addAccount(account1);
        assertEquals(1, passwordManagerEmpty.count());
        passwordManagerEmpty.addAccount(account2);
        assertEquals(2, passwordManagerEmpty.count());
        passwordManagerEmpty.addAccount(account3);
        assertEquals(3, passwordManagerEmpty.count());
    }

    @Test
    public void testAddAccountThere() {
        passwordManagerEmpty.addAccount(account1);
        assertEquals(1, passwordManagerEmpty.count());
        passwordManagerEmpty.addAccount(account1);
        assertTrue(passwordManagerEmpty.checkAccountAlreadyThere(account1));
        assertEquals(1, passwordManagerEmpty.count());
    }

    @Test
    public void testRemoveAccountEmpty() {
        passwordManagerEmpty.removeAccount("IG");
        assertEquals(0, passwordManagerEmpty.count());
    }

    @Test
    public void testRemoveAccount() {
        assertEquals(2, passwordManagerFull.count());
        passwordManagerFull.removeAccount("ssc");
        assertEquals(1, passwordManagerFull.count());
    }

    @Test
    public void testRemoveAccountMultiple() {
        assertEquals(2, passwordManagerFull.count());
        passwordManagerFull.removeAccount("ssc");
        assertEquals(1, passwordManagerFull.count());
        passwordManagerFull.removeAccount("IG");
        assertEquals(0, passwordManagerFull.count());
    }

    @Test
    public void testRemoveMultipleNotThere() {
        assertEquals(2, passwordManagerFull.count());
        passwordManagerFull.removeAccount("Facebook");
        assertEquals(2, passwordManagerFull.count());
        passwordManagerFull.removeAccount("twitter");
        assertEquals(2, passwordManagerFull.count());
    }

}
