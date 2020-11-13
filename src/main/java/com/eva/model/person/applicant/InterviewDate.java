package com.eva.model.person.applicant;

import static com.eva.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;

import com.eva.commons.util.DateUtil;

/**
 * Represents the interview date of an applicant.
 */
public class InterviewDate {
    public static final String MESSAGE_CONSTRAINTS = "Interview Date should have the format dd/MM/yyyy. "
            + "\n For example: 02112020 for 2nd November 2020";
    private final LocalDate interviewDate;

    /**
     * Creates an object representing the interview date of the applicant from a string
     *
     * @param date String representation of interview date.
     */
    public InterviewDate(String date) {
        requireNonNull(date);
        checkArgument(isValidInterviewDate(date), MESSAGE_CONSTRAINTS);
        this.interviewDate = parseDate(date);
    }

    private static LocalDate parseDate(String date) throws DateTimeException {
        return DateUtil.dateParsed(date);
    }

    /**
     * Checks the validity of the date.
     *
     * @param date String representation of date.
     * @return True if the date is given in dd/MM/yyyy format.
     */
    public static boolean isValidInterviewDate(String date) {
        try {
            parseDate(date);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Checks if the interview is over relative to the current date.
     *
     * @return True if the interview date is earlier than the current date.
     */
    public boolean isCompleted() {
        return this.interviewDate.compareTo(LocalDate.now()) < 0;
    }

    @Override
    public String toString() {
        return DateUtil.dateToString(interviewDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InterviewDate that = (InterviewDate) o;
        return interviewDate.equals(that.interviewDate);
    }
}
