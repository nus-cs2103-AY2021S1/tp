package seedu.address.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public interface TimeSlot {
    public LocalDateTime getStartDateTimeValue();
    public LocalDateTime getEndDateTimeValue();
    public DayOfWeek getDayOfWeek();
}
