package seedu.address.model.deck;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * A class which serves to identify a unique deck. No two decks can have the same deck name
 */
public class DeckName {

    public static final String MESSAGE_CONSTRAINTS = "Deck name cannot be blank";
    public static final String LENGTH_CONSTRAINTS = "Deck name cannot be longer than 100 characters";

    /**
     * Deck names cannot be null or empty
     * Deck names cannot be more than 100 characters long
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";
    public static final int MAXIMUM_LENGTH = 100;

    public final String deckName;

    /**
     * Constructs an {@code DeckName}.
     * @param deckName A valid deck name.
     */
    public DeckName(String deckName) {
        requireNonNull(deckName);
        checkArgument(isValidLength(deckName), LENGTH_CONSTRAINTS);
        checkArgument(isValidDeckName(deckName), MESSAGE_CONSTRAINTS);
        this.deckName = deckName;
    }

    /**
     * Returns true if the given string {@code test} is a valid deck name.
     * @param test String to be tested if it is a valid deck name.
     * @return True is the given string is a valid deck name.
     */
    public static boolean isValidDeckName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string {@code test} is of valid length.
     * @param test String to be tested if it is of valid length.
     * @return Truw if the given string is of valid length.
     */
    public static boolean isValidLength (String test) {
        return test.length() <= MAXIMUM_LENGTH;
    }

    public String getDeckName() {
        return deckName;
    }

    @Override
    public String toString() {
        return deckName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeckName // instanceof handles nulls
                && deckName.equals(((DeckName) other).deckName)); // state check
    }

    @Override
    public int hashCode() {
        return deckName.hashCode();
    }

}
