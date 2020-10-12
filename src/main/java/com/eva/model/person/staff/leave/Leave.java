package com.eva.model.person.staff.leave;

import static com.eva.commons.util.DateUtil.dateParsed;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Represents a Leave period in the address book.
 * Guarantees: immutable; date is valid if it is made;
 */
public class Leave {

    public static final String MESSAGE_CONSTRAINTS = "Leave date should be in the format dd/mm/yyyy";
    private static final String SINGLE_DAY_LEAVE = "Leave taken on %s for a total of %d days.";
    private static final int SINGLE_LEAVE_LENGTH = 1;
    private static final String MULTIPLE_DAY_LEAVE = "Leave taken from %s to %s for a total of %d days.";

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int leaveLength;

    /**
     * Constructs a {@code Leave}.
     *
     * @param date A valid leave start date.
     */
    public Leave(String date) {
        requireNonNull(date);
        this.startDate = dateParsed(date);
        this.endDate = startDate;
        leaveLength = SINGLE_LEAVE_LENGTH;
    }

    /**
     * Constructs a {@code Leave}.
     *
     * @param startDate A valid leave start date.
     * @param endDate   A valid leave end date.
     */
    public Leave(String startDate, String endDate) {
        requireNonNull(startDate, endDate);
        this.startDate = dateParsed(startDate);
        this.endDate = dateParsed(endDate);
        leaveLength = Period.between(this.startDate, this.endDate).getDays() + 1;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getLeaveLength() {
        return leaveLength;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Leave // instanceof handles nulls
                && (endDate.equals(((Leave) other).endDate)
                && startDate.equals(((Leave) other).startDate))); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate, startDate);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        if (startDate.equals(endDate)) {
            return String.format(SINGLE_DAY_LEAVE, startDate, leaveLength);
        } else {
            return String.format(MULTIPLE_DAY_LEAVE, startDate, endDate, leaveLength);
        }
    }
}


