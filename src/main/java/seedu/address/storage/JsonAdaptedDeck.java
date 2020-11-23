package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.DeckName;
import seedu.address.model.deck.entry.UniqueEntryList;
import seedu.address.model.play.scoring.QuizAttempt;

/**
 * Jackson-friendly version of {@link Deck}.
 */
class JsonAdaptedDeck {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Deck's %s field is missing!";
    private final String deckName;
    private final List<JsonAdaptedEntry> internalList = new ArrayList<>();
    private final List<JsonAdaptedQuizAttempt> quizAttempts = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDeck} with the given entry details.
     */
    @JsonCreator
    public JsonAdaptedDeck(@JsonProperty("deckname") String deckName,
        @JsonProperty("internallist") List<JsonAdaptedEntry> internalList,
        @JsonProperty("quizAttempts") List<JsonAdaptedQuizAttempt> quizAttempts) {
        this.deckName = deckName;
        if (internalList != null) {
            this.internalList.addAll(internalList);
        }
        if (quizAttempts != null) {
            this.quizAttempts.addAll(quizAttempts);
        }
    }

    /**
     * Converts a given {@code Deck} into this class for Jackson use.
     */
    public JsonAdaptedDeck(Deck source) {
        deckName = source.getDeckName().deckName;
        internalList.addAll(source.getEntryList().stream()
            .map(JsonAdaptedEntry::new)
            .collect(Collectors.toList()));
        quizAttempts.addAll(source.getQuizAttempts().stream()
            .map(JsonAdaptedQuizAttempt::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted entry object into the model's {@code Deck} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *                               entry.
     */
    public Deck toModelType() throws IllegalValueException {
        final UniqueEntryList modelInternalList = new UniqueEntryList();

        for (JsonAdaptedEntry entry : internalList) {
            modelInternalList.add(entry.toModelType());
        }

        final List<QuizAttempt> modelQuizAttempts = new ArrayList<>();

        for (JsonAdaptedQuizAttempt quizAttempt : quizAttempts) {
            modelQuizAttempts.add(quizAttempt.toModelType());
        }

        if (deckName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                DeckName.class.getSimpleName()));
        }
        if (!DeckName.isValidDeckName(deckName)) {
            throw new IllegalValueException(DeckName.MESSAGE_CONSTRAINTS);
        }
        final DeckName modelDeckName = new DeckName(deckName);

        final Deck deck = new Deck(modelDeckName);
        deck.setEntries(modelInternalList);
        deck.setQuizAttempts(modelQuizAttempts);
        return deck;
    }
}
