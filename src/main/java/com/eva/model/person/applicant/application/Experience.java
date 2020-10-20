package com.eva.model.person.applicant.application;

import java.time.LocalDate;
import java.time.DateTimeException;

/**
 * Represents an applicant's experience at a particular company, similar to what is seen in a resume.
 */
public class Experience {
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
        this.startDate = parseDate(startDate);
        this.endDate = parseDate(endDate);
        this.companyName = companyName;
        this.position = position;
        this.description = description;
    }

    private static LocalDate parseDate(String date) throws DateTimeException {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompanyName())
                .append("\nRole: ")
                .append(getPosition())
                .append("\nFrom: ")
                .append(getStartDate())
                .append("\nTo: ")
                .append(getEndDate())
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
