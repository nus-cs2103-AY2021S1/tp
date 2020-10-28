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
        if (status.toLowerCase().equals(RECEIVED.status)) {
            return RECEIVED;
        } else if (status.toLowerCase().equals(PROCESSING.status)) {
            return PROCESSING;
        } else if (status.toLowerCase().equals(ACCEPTED.status)) {
            return ACCEPTED;
        } else if (status.toLowerCase().equals(REJECTED.status)) {
            return REJECTED;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return status;
    }
}
