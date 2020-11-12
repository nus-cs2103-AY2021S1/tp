package seedu.address.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Objects implement TimeSlot so that clashes between it and other TimeSlots can be prevented in PlaNus.
 */
public interface TimeSlot {
    /**
     * Obtains the start time of a time slot in LocalDateTime.
     * @return a LocalDateTime object representing the starting time.
     */
    LocalDateTime getStartDateTimeValue();

    /**
     * Obtains the ending time of a time slot in LocalDateTime.
     * @return a LocalDateTime object representing the ending time.
     */
    LocalDateTime getEndDateTimeValue();

    /**
     * Obtains the day of the week of a time slot.
     * @return a DayOfWeek object representing the day of week of the time slot.
     */
    DayOfWeek getDayOfWeek();
}
