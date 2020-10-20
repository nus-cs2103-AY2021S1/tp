package com.eva.model.person.applicant.application;

import java.time.DateTimeException;
import java.time.LocalDate;
import static java.util.Objects.requireNonNull;

/**
 * Represents an applicant's education at a particular institution, similar to what is seen in a resume.
 */
public class Education {
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
        this.startDate = parseDate(startDate);
        this.endDate = parseDate(endDate);
        this.schoolName = schoolName;
    }

    private static LocalDate parseDate(String date) throws DateTimeException {
        requireNonNull(date);
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4, 8));
        return LocalDate.of(year, month, day);
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

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getSchoolName())
                .append("\nFrom: ")
                .append(getStartDate())
                .append(" To: ")
                .append(getEndDate())
                .append("\n");
        return builder.toString();
    }
}
