package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for PasswordManager class
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
        assertEquals(0, passwordManagerEmpty.getCount());
    }

    @Test
    public void testAddAccount() {
        assertEquals(0, passwordManagerEmpty.getCount());
        passwordManagerEmpty.addAccount(account1);
        assertEquals(1, passwordManagerEmpty.getCount());
        assertEquals(account1, passwordManagerEmpty.getAccount(0));
    }

    @Test
    public void testAddAccountMultiple() {
        assertEquals(0, passwordManagerEmpty.getCount());
        passwordManagerEmpty.addAccount(account1);
        assertEquals(1, passwordManagerEmpty.getCount());
        passwordManagerEmpty.addAccount(account2);
        assertEquals(2, passwordManagerEmpty.getCount());
        passwordManagerEmpty.addAccount(account3);
        assertEquals(3, passwordManagerEmpty.getCount());
        assertEquals(account1, passwordManagerEmpty.getAccount(0));
        assertEquals(account2, passwordManagerEmpty.getAccount(1));
        assertEquals(account3, passwordManagerEmpty.getAccount(2));
    }

    @Test
    public void testAddAccountThere() {
        assertEquals(2, passwordManagerFull.getCount());
        assertTrue(passwordManagerFull.containsAccount("IG", "sadia"));
        assertEquals(2, passwordManagerFull.getCount());
    }

    @Test
    public void testDoesNotContain() {
        Account a = new Account("SC", "skd", "123");
        assertFalse(passwordManagerFull.containsAccount("SC", "skd"));
        passwordManagerFull.addAccount(a);
        assertEquals(3, passwordManagerFull.getCount());
    }

    @Test
    public void testDoesContainSameApp() {
        assertFalse(passwordManagerFull.containsAccount("ssc", "sadia123"));
        assertEquals(2, passwordManagerFull.getCount());
    }

    @Test
    public void testDoesContainSameUser() {
        assertFalse(passwordManagerFull.containsAccount("cwl", "sadia"));
        assertEquals(2, passwordManagerFull.getCount());
    }


    @Test
    public void testRemoveAccountEmpty() {
        assertFalse(passwordManagerEmpty.removeAccount("IG", "sadia"));
        assertEquals(0, passwordManagerEmpty.getCount());
    }

    @Test
    public void testRemoveAccount() {
        assertEquals(2, passwordManagerFull.getCount());
        assertTrue(passwordManagerFull.removeAccount("ssc", "skd"));
        assertEquals(1, passwordManagerFull.getCount());
    }

    @Test
    public void testRemoveAccountSameApp() {
        assertEquals(2, passwordManagerFull.getCount());
        assertFalse(passwordManagerFull.removeAccount("IG", "sadia123"));
        assertEquals(2, passwordManagerFull.getCount());
    }

    @Test
    public void testRemoveAccountSameUsername() {
        assertEquals(2, passwordManagerFull.getCount());
        assertFalse(passwordManagerFull.removeAccount("cwl", "skd"));
        assertEquals(2, passwordManagerFull.getCount());
    }

    @Test
    public void testRemoveAccountMultiple() {
        assertEquals(2, passwordManagerFull.getCount());
        assertTrue(passwordManagerFull.removeAccount("ssc", "skd"));
        assertEquals(1, passwordManagerFull.getCount());
        assertTrue(passwordManagerFull.removeAccount("IG", "sadia"));
        assertEquals(0, passwordManagerFull.getCount());
    }

    @Test
    public void testRemoveMultipleNotThere() {
        assertEquals(2, passwordManagerFull.getCount());
        assertFalse(passwordManagerFull.removeAccount("Facebook", "123"));
        assertEquals(2, passwordManagerFull.getCount());
        assertFalse(passwordManagerFull.removeAccount("twitter", "456"));
        assertEquals(2, passwordManagerFull.getCount());
    }


}
