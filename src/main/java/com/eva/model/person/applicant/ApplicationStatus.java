package com.eva.model.person.applicant;

import static com.eva.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;



/**
 * Represents the application status of an applicant - accepted, rejected or processing.
 */
public class ApplicationStatus {
    public static final String MESSAGE_CONSTRAINTS =
            "Application Status should only contain the words: processing, accepted, rejected. It should not be blank";
    private PossibleApplicationStatus value;

    /**
     * Creates an object representing the application status of the applicant.
     *
     * @param status
     */
    public ApplicationStatus(String status) {
        requireNonNull(status);
        status = status.strip().toLowerCase();
        checkArgument(isValidApplicationStatus(status), MESSAGE_CONSTRAINTS);
        this.value = PossibleApplicationStatus.getStatus(status);
    }

    /**
     * Checks whether the application status is a valid status - accepted, rejected or processing.
     *
     * @param status
     * @return
     */
    public static boolean isValidApplicationStatus(String status) {
        try {
            PossibleApplicationStatus.getStatus(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

    /**
     * Sets the application status to processing.
     */
    public void setProcessing() {
        this.value = PossibleApplicationStatus.PROCESSING;
    }

    /**
     * Sets the application status to accepted.
     */
    public void setAccepted() {
        this.value = PossibleApplicationStatus.ACCEPTED;
    }

    /**
     * Sets the application status to rejected.
     */
    public void setRejected() {
        this.value = PossibleApplicationStatus.REJECTED;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
