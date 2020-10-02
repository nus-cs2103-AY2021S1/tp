package com.eva.model.applicant;

import static com.eva.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents the interview date of an applicant.
 */
public class InterviewDate {
    public static final String FORMAT = "DDMMYYYY";
    public static final String MESSAGE_CONSTRAINTS = "Interview Date should have the format: "
            + FORMAT
            + "\n For example: 02112020 for 2nd November 2020";
    private LocalDate interviewDate;

    /**
     * Creates an object representing the interview date of the applicant from a string
     * @param date String representation of interview date.
     */
    public InterviewDate(String date) {
        requireNonNull(date);
        checkArgument(isValidInterviewDate(date), MESSAGE_CONSTRAINTS);
        this.interviewDate = parseDate(date);
    }

    private static LocalDate parseDate(String date) throws DateTimeException {
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4, 8));
        return LocalDate.of(year, month, day);
    }

    /**
     * Checks the validity of the date.
     * @param date String representation of date.
     * @return True if the date is given in ddmmyyyy format.
     */
    public static boolean isValidInterviewDate(String date) {
        try {
            if (date.length() != 8) {
                return false;
            } else {
                parseDate(date);
                return true;
            }
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Checks if the interview is over relative to the current date.
     * @return True if the interview date is earlier than the current date.
     */
    public boolean isCompleted() {
        return this.interviewDate.compareTo(LocalDate.now()) < 0;
    }

    @Override
    public String toString() {
        return interviewDate.toString();
    }
}
