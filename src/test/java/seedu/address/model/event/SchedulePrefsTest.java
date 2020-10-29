package seedu.address.model.event;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class SchedulePrefsTest {

    private LocalDateTime dateTime = LocalDateTime.of(2020, 10, 29, 9, 0);
    private SchedulePrefs schedulePrefs = new SchedulePrefs(ScheduleViewMode.WEEKLY, dateTime);
    @Test
    public void setViewMode_nullViewMode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedulePrefs.setViewMode(null));
    }

    @Test
    public void setViewMode_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedulePrefs.setViewDateTime(null));
    }
}
