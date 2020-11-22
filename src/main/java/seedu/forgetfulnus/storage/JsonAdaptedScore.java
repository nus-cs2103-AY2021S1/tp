package seedu.forgetfulnus.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.forgetfulnus.commons.exceptions.IllegalValueException;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.score.Score;

public class JsonAdaptedScore {

    private final int score;
    private final int numQuestions;
    private final List<JsonAdaptedFlashCard> flashCards = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedScore} with the given flashcard details.
     */
    @JsonCreator
    public JsonAdaptedScore(@JsonProperty("score") int score,
                                @JsonProperty("numQuestions") int numQuestions,
                                @JsonProperty("wordsTested") List<JsonAdaptedFlashCard> flashCards) {
        this.score = score;
        this.numQuestions = numQuestions;
        if (flashCards != null) {
            this.flashCards.addAll(flashCards);
        }
    }

    /**
     * Converts a given {@code Score} into this class for Jackson use.
     */
    public JsonAdaptedScore(Score source) {
        score = source.getScore();
        numQuestions = source.getNumQuestions();

        flashCards.addAll(source.getFlashcards().stream()
                .map(JsonAdaptedFlashCard::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted score object into the model's {@code Score} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted flashcard.
     */
    public Score toModelType() throws IllegalValueException {
        final List<FlashCard> testedFlashCards = new ArrayList<>();
        for (JsonAdaptedFlashCard card : flashCards) {
            testedFlashCards.add(card.toModelType());
        }

        if (score < 0) {
            throw new IllegalValueException("Invalid number for score");
        }
        if (numQuestions < 0) {
            throw new IllegalValueException("Number of questions cannot be less than 0");
        }
        return new Score(score, numQuestions, testedFlashCards);
    }
}
