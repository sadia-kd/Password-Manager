package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventLogTest {
    private Account account;
    private PasswordManager passwordManager;

    @BeforeEach
    public void setUp() {
        account = new Account("Sadia", "ig", "123");
        passwordManager = new PasswordManager();

    }

    @Test
    public void testDescription() {
        passwordManager.addAccount(account);
        EventLog.getInstance();
        //assertEquals();
    }
}
