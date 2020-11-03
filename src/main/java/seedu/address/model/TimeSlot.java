package seedu.address.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Objects implement TimeSlot so that clashes between it and other TimeSlots can be prevented in PlaNus.
 */
public interface TimeSlot {
    public LocalDateTime getStartDateTimeValue();
    public LocalDateTime getEndDateTimeValue();
    public DayOfWeek getDayOfWeek();
}
