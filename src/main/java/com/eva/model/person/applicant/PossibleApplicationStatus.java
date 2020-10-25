package com.eva.model.person.applicant;

public enum PossibleApplicationStatus {

    RECEIVED("received"),
    PROCESSING("processing"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    private String status;

    PossibleApplicationStatus(String status) {
        this.status = status;
    }

    public static PossibleApplicationStatus getStatus(String status) {
        if (status.equals(RECEIVED.status)) {
            return RECEIVED;
        } else if (status.equals(PROCESSING.status)) {
            return PROCESSING;
        } else if (status.equals(ACCEPTED.status)) {
            return ACCEPTED;
        } else if (status.equals(REJECTED.status)) {
            return REJECTED;
        } else {
            assert false;
            // should not reach here
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return status;
    }
}
