package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.getTypicalScheduler;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class SchedulerTest {
    private final Scheduler scheduler = new Scheduler();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), scheduler.getEventsList());
        assertEquals(Collections.emptyList(), scheduler.getVEvents());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Scheduler newData = getTypicalScheduler();
        scheduler.resetData(newData);
        assertEquals(scheduler, newData);
    }


    @Test
    public void getVEvents_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> scheduler.getVEvents().remove(0));
    }

    // event list test
    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.addEvent(null));
    }

}
