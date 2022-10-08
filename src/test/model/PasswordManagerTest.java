package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordManagerTest {
    private PasswordManager passwordManager;
    private Account account1;
    private Account account2;
    private Account account3;

    @BeforeEach
    public void setUp() {
        this.passwordManager = new PasswordManager();
        this.account1 = new Account("IG", "sadia", "cpsc");
        this.account2 = new Account("ssc", "skd", "210");
        this.account3 = new Account("SC", "sd", "cpsc210");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, passwordManager.count());
    }

    @Test
    public void testAddAccount() {
        passwordManager.addAccount(account1);
        assertEquals(1, passwordManager.count());
    }

    @Test
    public void testAddAccountMultiple() {
        passwordManager.addAccount(account1);
        assertEquals(1, passwordManager.count());
        passwordManager.addAccount(account2);
        assertEquals(2, passwordManager.count());
        passwordManager.addAccount(account3);
        assertEquals(3, passwordManager.count());
    }

    @Test
    public void testAddAccountThere() {
        passwordManager.addAccount(account1);
        assertEquals(1, passwordManager.count());
        passwordManager.addAccount(account1);
        assertTrue(passwordManager.checkAccountAlreadyThere(account1));
        assertEquals(1, passwordManager.count());
    }

}
