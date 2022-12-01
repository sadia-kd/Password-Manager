package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private Event event;
    private Date date;

    @BeforeEach
    public void setUp() {
        event = new Event("Account removes");

    }

    @Test
    public void testEvent() {
        assertEquals("Account removes", event.getDescription());

    }
}
