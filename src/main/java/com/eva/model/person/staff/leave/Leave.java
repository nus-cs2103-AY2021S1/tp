package com.eva.model.person.staff.leave;

import static com.eva.commons.util.AppUtil.checkArgument;
import static com.eva.commons.util.DateUtil.DATE_TIME_FORMATTER;
import static com.eva.commons.util.DateUtil.isValidDate;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;


/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLeaveDate(String)}
 */
public class Leave {

    public static final String MESSAGE_CONSTRAINTS = "Leave date should be in the format dd/mm/yyyy";
    private static final String SINGLE_DAY_LEAVE = "Leave taken on %s";
    private static final String MULTIPLE_DAY_LEAVE = "Leave taken from %s to %s";

    private final LocalDate from;
    private final LocalDate to;
    private final Period period;

    /**
     * Constructs a {@code Leave}.
     *
     * @param date A valid leave start date.
     */
    public Leave(String date) {
        requireNonNull(date);
        checkArgument(isValidLeaveDate(date), MESSAGE_CONSTRAINTS);
        this.from = LocalDate.parse(date, DATE_TIME_FORMATTER);
        this.to = from;
        period = Period.ofDays(1);
    }

    /**
     * Constructs a {@code Leave}.
     *
     * @param from A valid leave start date.
     * @param to   A valid leave end date.
     */
    public Leave(String from, String to) {
        requireNonNull(from, to);
        checkArgument(isValidLeaveDate(from), MESSAGE_CONSTRAINTS);
        checkArgument(isValidLeaveDate(to), MESSAGE_CONSTRAINTS);
        this.from = LocalDate.parse(from, DATE_TIME_FORMATTER);
        this.to = LocalDate.parse(to, DATE_TIME_FORMATTER);
        period = Period.between(this.from, this.to);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidLeaveDate(String test) {
        return isValidDate(test);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Leave // instanceof handles nulls
                && (to.equals(((Leave) other).to)
                && from.equals(((Leave) other).from))); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, from);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        if (from.equals(to)) {
            return String.format(SINGLE_DAY_LEAVE, from);
        } else {
            return String.format(MULTIPLE_DAY_LEAVE, from, to);
        }
    }
}


