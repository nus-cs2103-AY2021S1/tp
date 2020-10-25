package com.eva.model.person.applicant.application;

import static com.eva.commons.util.AppUtil.checkArgument;
import static com.eva.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;

import com.eva.commons.util.DateUtil;

/**
 * Represents an applicant's experience at a particular company, similar to what is seen in a resume.
 */
public class Experience {
    public static final String MESSAGE_CONSTRAINTS = "Dates should have the format dd/MM/yyyy. "
            + "\n For example: 02112020 for 2nd November 2020";
    private LocalDate startDate;
    private LocalDate endDate;
    private String companyName;
    private String position;
    private String description;

    /**
     * Creates an experience object.
     * @param startDate The start date.
     * @param endDate The end date.
     * @param companyName The name of the company.
     * @param position The position held.
     * @param description The description of the job.
     */
    public Experience(String startDate, String endDate, String companyName, String position, String description) {
        requireAllNonNull(startDate, endDate, companyName);
        checkArgument(isValidDate(startDate), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(endDate), MESSAGE_CONSTRAINTS);
        this.startDate = parseDate(startDate);
        this.endDate = parseDate(endDate);
        this.companyName = companyName;
        this.position = position;
        this.description = description;
    }

    private static LocalDate parseDate(String date) throws DateTimeException {
        return DateUtil.dateParsed(date);
    }

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

    public String getCompanyName() {
        return companyName;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompanyName())
                .append("\nRole: ")
                .append(getPosition())
                .append("\nFrom: ")
                .append(DateUtil.dateToString(this.startDate))
                .append("\nTo: ")
                .append(DateUtil.dateToString(this.endDate))
                .append("\nDescription: ")
                .append(getDescription())
                .append("\n");
        return builder.toString();
        /*
        builder.append(getCompanyName())
                .append("\nFrom: ")
                .append(getStartDate())
                .append(" To: ")
                .append(getEndDate());
        return builder.toString();
        */
    }
}
