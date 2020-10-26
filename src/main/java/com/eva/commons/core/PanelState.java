package com.eva.commons.core;

public enum PanelState {
    STAFF_LIST("Staff List"),
    STAFF_PROFILE("Staff Profile"),
    APPLICANT_LIST("Applicant List"),
    APPLICANT_PROFILE("Applicant Profile");

    private final String panelState;

    PanelState(String panelState) {
        this.panelState = panelState;
    }

    @Override
    public String toString() {
        return panelState;
    }
}
