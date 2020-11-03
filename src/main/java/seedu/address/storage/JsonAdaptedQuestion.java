package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.academic.question.Question;
import seedu.address.model.student.academic.question.SolvedQuestion;
import seedu.address.model.student.academic.question.UnsolvedQuestion;

/**
 * Jackson-friendly version of {@code Question}.
 */
public class JsonAdaptedQuestion {

    public static final String MISSING_QUESTION_FIELD_MESSAGE_FORMAT = "Question's %s field is missing!";

    private final boolean isResolved;
    private final String question;
    private final String solution;

    /**
     * Constructs a {@code JsonAdaptedQuestion} with the given {@code question}.
     */
    @JsonCreator
    public JsonAdaptedQuestion(@JsonProperty("status") boolean isResolved, @JsonProperty("question") String question,
                               @JsonProperty("solution") String solution) {
        this.isResolved = isResolved;
        this.question = question;
        this.solution = solution;
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     */
    public JsonAdaptedQuestion(Question source) {
        this.isResolved = source.isResolved();
        this.question = source.question;
        if (isResolved) {
            SolvedQuestion solved = (SolvedQuestion) source;
            this.solution = solved.solution;
        } else {
            this.solution = "";
        }
    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted detail.
     */
    public Question toModelType() throws IllegalValueException {
        return isResolved ? createModelSolved() : createModelUnsolved();
    }

    private UnsolvedQuestion createModelUnsolved() throws IllegalValueException {
        checkQuestion();
        return new UnsolvedQuestion(question);
    }

    private SolvedQuestion createModelSolved() throws IllegalValueException {
        checkQuestion();

        if (solution == null || solution.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_QUESTION_FIELD_MESSAGE_FORMAT, "solution"));
        }
        if (!SolvedQuestion.isValidSolution(solution)) {
            throw new IllegalValueException(SolvedQuestion.MESSAGE_SOLUTION_CONSTRAINTS);
        }

        return new SolvedQuestion(question, solution);
    }

    private void checkQuestion() throws IllegalValueException {
        if (question == null || question.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_QUESTION_FIELD_MESSAGE_FORMAT, "body"));
        }
        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }
    }

}
