package com.eva.model.comment;

public class Title {
    private final String title;

    Title(String title) {
        this.title = title;
    }

    public String getTitleDescription() {
        return this.title;
    }

    public boolean equals(Title t) {
        return this.title.equals(t.getTitleDescription());
    }
}
