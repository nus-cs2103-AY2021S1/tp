package seedu.address.model.event;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.Assert;

import static org.junit.jupiter.api.Assertions.*;

public class EventRecurrenceTest {

    @Test
    public void checkWhichRecurrence_validInput_success() {
        assertEquals(EventRecurrence.DAILY,EventRecurrence.checkWhichRecurrence("Daily"));
        assertEquals(EventRecurrence.WEEKLY,EventRecurrence.checkWhichRecurrence("Weekly"));
        assertEquals(EventRecurrence.NONE,EventRecurrence.checkWhichRecurrence("None"));
    }

    @Test
    public void checkWhichRecurrence_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> EventRecurrence.checkWhichRecurrence(""));
        assertThrows(IllegalArgumentException.class, () -> EventRecurrence.checkWhichRecurrence("   "));
        assertThrows(IllegalArgumentException.class, () -> EventRecurrence.checkWhichRecurrence("123"));
        assertThrows(IllegalArgumentException.class, () -> EventRecurrence.checkWhichRecurrence("hahahah"));
    }

    @Test
    public void checkWhichRecurRule_validInput_success() {
        assertEquals(EventRecurrence.DAILY,EventRecurrence.checkWhichRecurRule("FREQ=DAILY;INTERVAL=1"));
        assertEquals(EventRecurrence.WEEKLY,EventRecurrence.checkWhichRecurRule("FREQ=WEEKLY;INTERVAL=1"));
        assertEquals(EventRecurrence.NONE,EventRecurrence.checkWhichRecurRule(""));
    }

    @Test
    public void checkWhichRecurRulee_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> EventRecurrence.checkWhichRecurRule("   "));
        assertThrows(IllegalArgumentException.class, () -> EventRecurrence.checkWhichRecurRule("123"));
        assertThrows(IllegalArgumentException.class, () -> EventRecurrence.checkWhichRecurRule("hahahah"));
    }

}
