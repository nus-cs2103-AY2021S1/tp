package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.play.scoring.BinaryScoring;
import seedu.address.model.play.scoring.QuestionAttempt;
import seedu.address.model.play.scoring.Scoring;


public class JsonAdaptedQuestionAttempt {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Question Attempts's %s field is missing!";
    private final String answer;
    private final String guess;
    @JsonProperty("score")
    private final double score;

    /**
     * Constructs a {@code JsonAdaptedQuestionAttempt} with the given {@code score}.
     */
    @JsonCreator
    public JsonAdaptedQuestionAttempt(@JsonProperty("answer") String answer,
        @JsonProperty("guess") String guess) {
        this.answer = answer;
        this.guess = guess;
        Scoring scoringMethod = new BinaryScoring();
        this.score = scoringMethod.computeScore(answer, guess);
    }

    /**
     * Converts a given {@code score} into this class for Jackson use.
     */
    public JsonAdaptedQuestionAttempt(QuestionAttempt source) {
        answer = source.getAnswer();
        guess = source.getGuess();
        score = source.getScore();
    }

    //Dummy Constructor

    /**
     * Empty Constructor
     */
    public JsonAdaptedQuestionAttempt() {
        answer = "";
        guess = "";
        score = 1;
    }

    public String getAnswer() {
        return answer;
    }

    public String getGuess() {
        return guess;
    }

    public double getScore() {
        return score;
    }

    /**
     * Converts this Jackson-friendly adapted Score object into the model's {@code score} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *                               Score.
     */
    public QuestionAttempt toModelType() throws IllegalValueException {
        if (answer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                QuestionAttempt.class.getSimpleName()));
        }
        if (guess == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                QuestionAttempt.class.getSimpleName()));
        }
        return new QuestionAttempt(answer, guess, score);
    }
}
