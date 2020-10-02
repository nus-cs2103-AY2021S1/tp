package com.eva.model.applicant;

import static com.eva.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;


/**
 * Represents the application status of an applicant - accepted, rejected or processing.
 */
public class ApplicationStatus {
    public static final String MESSAGE_CONSTRAINTS =
            "Application Status should only contain the words: processing, accepted, rejected. It should not be blank";
    public static final String[] POSSIBLE_STATUSES = {"processing", "accepted", "rejected"};
    private String status;

    /**
     * Creates an object representing the application status of the applicant.
     * @param status
     */
    public ApplicationStatus(String status) {
        requireNonNull(status);
        status = status.strip().toLowerCase();
        checkArgument(isValidApplicationStatus(status), MESSAGE_CONSTRAINTS);
        this.status = status;
    }

    /**
     * Checks whether the application status is a valid status - accepted, rejected or processing.
     * @param status
     * @return
     */
    public static Boolean isValidApplicationStatus(String status) {
        return Arrays.asList(POSSIBLE_STATUSES).contains(status);
    }

    public void setProcessing() {
        this.status = POSSIBLE_STATUSES[0];
    }

    public void setAccepted() {
        this.status = POSSIBLE_STATUSES[1];
    }

    public void setRejected() {
        this.status = POSSIBLE_STATUSES[2];
    }

    @Override
    public String toString() {
        return status;
    }
}
