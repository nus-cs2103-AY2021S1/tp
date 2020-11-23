package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.play.Score;
import seedu.address.model.play.scoring.BinaryScoring;
import seedu.address.model.play.scoring.QuestionAttempt;
import seedu.address.model.play.scoring.QuizAttempt;

/**
 * Jackson-friendly version of {@link Entry}.
 */
class JsonAdaptedQuizAttempt {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Quiz Attempts's %s field is missing!";

    private JsonAdaptedScore score;
    private int duration; // time in seconds
    private LocalDateTime takenAt;
    private List<JsonAdaptedQuestionAttempt> questionAttempts = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedQuizAttempt} with the given entry details.
     */
    @JsonCreator
    public JsonAdaptedQuizAttempt(@JsonProperty("score") JsonAdaptedScore score,
        @JsonProperty("duration") int duration,
        @JsonProperty("takenAt") LocalDateTime takenAt,
        @JsonProperty("questionAttempts") List<JsonAdaptedQuestionAttempt> questionAttempts) {
        this.score = score;
        this.duration = duration;
        this.takenAt = takenAt;
        if (questionAttempts != null) {
            this.questionAttempts.addAll(questionAttempts);
        }
    }

    /**
     * Converts a given {@code QuizAttempt} into this class for Jackson use.
     */
    public JsonAdaptedQuizAttempt(QuizAttempt source) {

        this.score = new JsonAdaptedScore(source.getScore());
        this.duration = source.getDuration();
        this.takenAt = source.getTakenAt();
        this.questionAttempts.addAll(source.getQuestionAttempts().stream()
            .map(JsonAdaptedQuestionAttempt::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted entry object into the model's {@code Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *                               entry.
     */
    public QuizAttempt toModelType() throws IllegalValueException {

        if (score == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                JsonAdaptedScore.class.getSimpleName()));
        }
        if (!Score.isValidScore(score.getMaxScore())) {
            throw new IllegalValueException(Score.MESSAGE_CONSTRAINTS);
        }

        if (!Score.isValidScore(score.getYourScore())) {
            throw new IllegalValueException(Score.MESSAGE_CONSTRAINTS);
        }
        final Score modelScore = score.toModelType();
        final int modelDuration = duration;
        final LocalDateTime modelTakenAt = takenAt;
        final List<QuestionAttempt> modelQuestionAttempts = new ArrayList<>();

        final QuizAttempt modelQuizAttempt = new QuizAttempt(new BinaryScoring());
        modelQuizAttempt.setScore(modelScore);
        modelQuizAttempt.setTakenAt(modelTakenAt);
        modelQuizAttempt.setDuration(modelDuration);
        modelQuizAttempt.setQuestionAttempts(modelQuestionAttempts);

        return modelQuizAttempt;
    }

}
