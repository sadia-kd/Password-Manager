package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {
    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    public void setUp() {
        event1 = new Event("Account Added");
        event2 = new Event("Account 2 Added");
        event3 = new Event("Account 3 Added");

        EventLog eventLog = EventLog.getInstance();
        eventLog.logEvent(event1);
        eventLog.logEvent(event2);
        eventLog.logEvent(event3);
    }

    @Test
    public void testLogEvent() {
        List<Event> eventList = new ArrayList<>();

        EventLog el = EventLog.getInstance();
        for (Event event : el) {
            eventList.add(event);
        }

        assertTrue(eventList.contains(event1));
        assertTrue(eventList.contains(event2));
        assertTrue(eventList.contains(event3));
    }

    @Test
    public void testClearMethod() {
        EventLog el = EventLog.getInstance();
        el.clear();
        Iterator<Event> iterator = el.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }
}
