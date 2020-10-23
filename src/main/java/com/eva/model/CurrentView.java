package com.eva.model;

import java.util.Optional;

import com.eva.model.person.Person;

public class CurrentView<P extends Person> {

    private final P currentView;

    public CurrentView() {
        this.currentView = null;
    }

    public CurrentView(P currentView) {
        this.currentView = currentView;
    }

    public Optional<P> getCurrentView() {
        return Optional.ofNullable(currentView);
    }
}
