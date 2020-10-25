package com.eva.model.person.applicant.application;

import static com.eva.commons.util.AppUtil.checkArgument;
import static com.eva.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;

import com.eva.commons.util.DateUtil;

/**
 * Represents an applicant's education at a particular institution, similar to what is seen in a resume.
 */
public class Education {
    public static final String MESSAGE_CONSTRAINTS = "Dates should have the format dd/MM/yyyy. "
            + "\n For example: 02112020 for 2nd November 2020";

    private LocalDate startDate;
    private LocalDate endDate;
    private String schoolName;

    /**
     * Creates an Education object for one institution.
     * @param startDate The start date of that particular education.
     * @param endDate The end date of that particular education.
     * @param schoolName The name of the institution.
     */
    public Education(String startDate, String endDate, String schoolName) {
        requireAllNonNull(startDate, endDate, schoolName);
        checkArgument(isValidDate(startDate), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(endDate), MESSAGE_CONSTRAINTS);
        this.startDate = parseDate(startDate);
        this.endDate = parseDate(endDate);
        this.schoolName = schoolName;
    }

    private static LocalDate parseDate(String date) throws DateTimeException {
        return DateUtil.dateParsed(date);
    }

    /**
     * Checks the validity of the date.
     * @param date String representation of date.
     * @return True if the date is given in dd/MM/yyyy format.
     */
    private static boolean isValidDate(String date) {
        try {
            parseDate(date);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getSchoolName() {
        return schoolName;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getSchoolName())
                .append("\nFrom: ")
                .append(DateUtil.dateToString(this.startDate))
                .append(" To: ")
                .append(DateUtil.dateToString(this.endDate))
                .append("\n");
        return builder.toString();
    }
}
