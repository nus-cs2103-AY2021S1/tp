package com.eva.model.comment;

import static com.eva.logic.parser.CliSyntax.PREFIX_DATE;
import static com.eva.logic.parser.CliSyntax.PREFIX_DESC;
import static com.eva.logic.parser.CliSyntax.PREFIX_TITLE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.eva.logic.parser.ArgumentMultimap;
import com.eva.logic.parser.ArgumentTokenizer;

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
                && title.getTitleDescription().equals(((Comment) other).getTitle().getTitleDescription())
                && description.equals(((Comment) other).description)
                && date.equals(((Comment) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, date, title.getTitle());
    }

    /**
     * Checks if provided input is a valid comment
     * @param comment
     * @return
     */
    public static boolean isValidComment(String comment) {
        ArgumentMultimap argMultmap = ArgumentTokenizer.tokenize(comment,
                PREFIX_TITLE, PREFIX_DATE, PREFIX_DESC);
        boolean hasTitle = !argMultmap.getValue(PREFIX_TITLE).isEmpty();
        boolean hasDate = !argMultmap.getValue(PREFIX_DATE).isEmpty();
        boolean hasDescription = !argMultmap.getValue(PREFIX_DESC).isEmpty();
        return hasTitle && hasDate && hasDescription;
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
        return this.title.getTitleDescription() + ": " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns true if comment is same as otherComment.
     */
    public boolean isSameComment(Comment otherComment) {
        if (otherComment == this) {
            return true;
        }

        return otherComment != null
                && otherComment.getTitle().equals(getTitle())
                && otherComment.getDate().equals(getDate())
                && otherComment.getDescription().equals(getDescription());
    }
}
