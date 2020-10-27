package com.eva.model.current.view;

import java.util.Optional;

import com.eva.model.person.applicant.Applicant;


public class CurrentViewApplicant {

    private final Applicant currentView;

    public CurrentViewApplicant() {
        this.currentView = null;
    }

    public CurrentViewApplicant(Applicant currentView) {
        this.currentView = currentView;
    }

    public Optional<Applicant> getCurrentView() {
        return Optional.ofNullable(currentView);
    }
}
