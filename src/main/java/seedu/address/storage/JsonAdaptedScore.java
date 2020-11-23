package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.play.Score;


public class JsonAdaptedScore {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Score's %s field is missing!";
    private double maxScore;
    private double yourScore;

    /**
     * Constructs a {@code JsonAdaptedScore} with the given {@code score}.
     */
    @JsonCreator
    public JsonAdaptedScore(@JsonProperty("maxScore") double maxScore,
        @JsonProperty("yourScore") double yourScore) {
        this.maxScore = maxScore;
        this.yourScore = yourScore;
    }

    /**
     * Converts a given {@code score} into this class for Jackson use.
     */
    public JsonAdaptedScore(Score source) {
        maxScore = source.getMaxScore();
        yourScore = source.getYourScore();
    }

    //Dummy Constructor

    /**
     * Empty Constructor
     */
    public JsonAdaptedScore() {
        maxScore = 1;
        yourScore = 1;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public double getYourScore() {
        return yourScore;
    }

    /**
     * Converts this Jackson-friendly adapted Score object into the model's {@code score} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *                               Score.
     */
    public Score toModelType() throws IllegalValueException {
        if (!Score.isValidScore(maxScore)) {
            throw new IllegalValueException(Score.MESSAGE_CONSTRAINTS);
        }

        if (!Score.isValidScore(yourScore)) {
            throw new IllegalValueException(Score.MESSAGE_CONSTRAINTS);
        }

        return new Score(maxScore, yourScore);

    }

}
