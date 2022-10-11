package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Account class
class AccountTest {
    private Account account;

    @BeforeEach
    public void setUp() {
        this.account = new Account("IG", "sadiakd", "cpsc210");
    }

    @Test
    public void testConstructor() {
        assertEquals("IG", account.getApplicationName());
        assertEquals("sadiakd", account.getUsername());
        assertEquals("cpsc210", account.getPassword());
    }

}