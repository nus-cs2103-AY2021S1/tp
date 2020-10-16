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

}
