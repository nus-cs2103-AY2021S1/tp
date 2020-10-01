package seedu.address.model.applicant;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ApplicationStatus {
    public static final String MESSAGE_CONSTRAINTS =
            "Application Status should only contain the words: processing, accepted, rejected. It should not be blank";
    public static final String[] possibleStatus = {"processing", "accepted", "rejected"};
    public String status;

    public ApplicationStatus(String status) {
        requireNonNull(status);
        status = status.strip().toLowerCase();
        checkArgument(isValidApplicationStatus(status), MESSAGE_CONSTRAINTS);
        this.status = status;
    }

    public static Boolean isValidApplicationStatus(String status) {
        return Arrays.asList(possibleStatus).contains(status);
    }

    public void setProcessing() {
        this.status = possibleStatus[0];
    }

    public void setAccepted() {
        this.status = possibleStatus[1];
    }

    public void setRejected() {
        this.status = possibleStatus[2];
    }

    @Override
    public String toString() {
        return status;
    }
}
