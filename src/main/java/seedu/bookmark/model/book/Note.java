package seedu.bookmark.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.util.AppUtil.checkArgument;

public class Note {
    public static final String MESSAGE_CONSTRAINTS =
            "Note title and text should not start with a whitespace.\n"
            + "Note title should have 1 to 120 characters. Note text should have 1 to 1000 characters.";
    /**
     * The first character of the note must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String TITLE_VALIDATION_REGEX = "^[^ ].{0,120}$";
    public static final String TEXT_VALIDATION_REGEX = "^[^ ].{0,1000}$";

    public final String title;
    public final String text;

    /**
     * Constructs an {@code Note}.
     *
     * @param title A valid title string.
     * @param text A valid text string.
     */
    public Note(String title, String text) {
        assert (title != null && text != null);
        requireNonNull(title);
        requireNonNull(text);
        checkArgument(isValidNote(title, text), MESSAGE_CONSTRAINTS);
        this.title = title;
        this.text = text;
    }

    /**
     * Returns if a given title and text string is a valid note.
     */
    public static boolean isValidNote(String title, String text) {
        return title.matches(TITLE_VALIDATION_REGEX) && text.matches(TEXT_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return title + ":" + text;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                       || (other instanceof Note // instanceof handles nulls
                                   && text.equals(((Note) other).text)
                                   && title.equals(((Note) other).title)); // state check
    }

    @Override
    public int hashCode() {
        return (title + text).hashCode();
    }
}
