package seedu.flashnotes.model.deck;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.flashnotes.model.Model;

/**
 * Deck is the tag which is given to every card.
 */
public class Deck {

    public static final String MESSAGE_CONSTRAINTS_LENGTH =
            "Deck name should not be blank and should be less than or equal to 40 characters. "
                    + "Current number of characters in deck name is %d.";
    public static final String MESSAGE_CONSTRAINTS_STATISTIC =
            "ResultStatistic should be a numeric value between 0 and 100.";

    private static final String RESERVED_DECK_NAME = "list";
    private static final String DEFAULT_DECK_NAME = "Default";

    public static final String MESSAGE_CONSTRAINTS_RESERVED = "Deck name should not be the reserved deck name: "
            + RESERVED_DECK_NAME;

    // Identity fields
    private String deckName;
    private String resultStatistics;

    /**
     * Initializes a Deck that contains the Deck Name and Result Statistics if any.
     *
     * @param name description of deck
     */
    public Deck(String name) {
        requireNonNull(name);
        this.deckName = name;
        this.resultStatistics = "-1.0";
    }


    public String getDeckName() {
        return deckName;
    }

    public String getResultStatistics() {
        return resultStatistics;
    }

    public static String getDefaultDeckName() {
        return DEFAULT_DECK_NAME;
    }

    public static String getReservedDeckName() {
        return RESERVED_DECK_NAME;
    }

    public void setDeckName(String deckName) {
        requireNonNull(deckName);
        this.deckName = deckName;
    }

    public void setResultStatistics(String resultStatistics) {
        requireNonNull(resultStatistics);
        this.resultStatistics = resultStatistics;
    }

    /**
     * Returns true if a given string is a valid deck name.
     */
    public static boolean isValidDeckLength(String test) {
        return !test.isBlank() && test.length() <= 40;
    }

    public static boolean isValidDeckReservedName(String test) {
        return !test.equals(Model.getReservedDeckName());
    }

    /**
     * Returns true if both Decks have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two decks.
     */
    public boolean isSameDeck(Deck otherDeck) {
        if (otherDeck == this) {
            return true;
        }

        return otherDeck != null
                && otherDeck.getDeckName().equals(getDeckName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deck deck = (Deck) o;
        return Objects.equals(deckName, deck.deckName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deckName);
    }
}
