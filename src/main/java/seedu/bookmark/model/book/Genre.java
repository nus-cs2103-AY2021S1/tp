package seedu.bookmark.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.util.AppUtil.checkArgument;

/**
 * Represents a Book's genre.
 * Guarantees: immutable; is valid as declared in {@link #isValidGenre(String)}
 */
public class Genre {

    public static final int MAX_GENRE_LENGTH = 60;

    public static final String MESSAGE_CONSTRAINTS =
            "Genre should only contain alphanumeric characters and spaces, and it should not be blank. \n"
            + String.format("Maximum of %d characters allowed, including spaces.", MAX_GENRE_LENGTH);

    /*
     * The first character of the genre must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input. Max of 60 characters.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]"
            + "{0," + (MAX_GENRE_LENGTH - 1) + "}$";

    public final String value;

    /**
     * Constructs an {@code Genre}.
     *
     * @param genre A valid genre string.
     */
    public Genre(String genre) {
        requireNonNull(genre);
        checkArgument(isValidGenre(genre), MESSAGE_CONSTRAINTS);
        value = genre;
    }

    /**
     * Returns if a given string is a valid genre.
     */
    public static boolean isValidGenre(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Genre // instanceof handles nulls
                && value.toLowerCase().equals(((Genre) other).value.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
