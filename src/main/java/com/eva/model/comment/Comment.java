package com.eva.model.comment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Comment {

    public final LocalDate date;
    public final Title title;
    private String description;

    /**
     * Create comment object
     * @param date date of comment
     * @param description description of comment
     * @param title title of comment
     */
    public Comment(LocalDate date, String description, String title) {
        this.date = date;
        this.description = description;
        this.title = new Title(title);
    }

    /**
     * Creates Comment object when deleting comments
     * @param title title of comment to delete
     */
    public Comment(String title) {
        this.date = null;
        this.description = null;
        this.title = new Title(title);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Comment // instanceof handles nulls
                && title.getTitle().equals(((Comment) other).getTitle().getTitle())
                && description.equals(((Comment) other).description)
                && date.equals(((Comment) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, date);
    }

    public static boolean isValidComment(String comment) {
        return true;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    public Title getTitle() {
        return this.title;
    }

    public void update(String description) {
        this.description = description;
    }

    public String toString() {
        return this.title.getTitle() + ": " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
