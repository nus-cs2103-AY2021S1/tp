package seedu.address.model.event;

import org.junit.jupiter.api.Test;
import seedu.address.model.UserPrefs;

import java.time.LocalDateTime;

import static seedu.address.testutil.Assert.assertThrows;

public class SchedulePrefsTest {

    LocalDateTime dateTime = LocalDateTime.of(2020, 10, 29, 9, 0);
    SchedulePrefs schedulePrefs = new SchedulePrefs(ScheduleViewMode.WEEKLY, dateTime);
    @Test
    public void setViewMode_nullViewMode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedulePrefs.setViewMode(null));
    }

    @Test
    public void setViewMode_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedulePrefs.setViewDateTime(null));
    }
}
