package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ScheduleCommandTest {

    @Test
    public void equals() {
        LocalDate date1 = LocalDate.of(2020, 11, 3);
        LocalDate date2 = LocalDate.of(2020, 11, 4);

        ScheduleCommand scheduleCommand1 = new ScheduleCommand(date1);
        ScheduleCommand scheduleCommand2 = new ScheduleCommand(date2);

        // same object -> true
        assertTrue(scheduleCommand1.equals(scheduleCommand1));

        // different object -> false
        assertFalse(scheduleCommand1.equals(scheduleCommand2));

        // same date return true
        ScheduleCommand scheduleCommand1Copy = new ScheduleCommand(date1);
        assertTrue(scheduleCommand1.equals(scheduleCommand1Copy));

        // null return false
        assertFalse(scheduleCommand1.equals(null));
    }


}
