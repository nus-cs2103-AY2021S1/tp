package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;
import seedu.address.model.student.question.UnsolvedQuestion;

/**
 * Jackson-friendly version of {@code Question}.
 */
public class JsonAdaptedQuestion {

    private final boolean status;
    private final String question;
    private final String solution;

    /**
     * Constructs a {@code JsonAdaptedQuestion} with the given {@code question}.
     */
    @JsonCreator
    public JsonAdaptedQuestion(@JsonProperty("status") boolean status, @JsonProperty("question") String question,
                               @JsonProperty("solution") String solution) {
        this.status = status;
        this.question = question;
        this.solution = solution;
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     */
    public JsonAdaptedQuestion(Question source) {
        this.status = source.isResolved();
        this.question = source.question;
        if (status) {
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
        return status ? createModelSolved() : createModelUnsolved();
    }

    private UnsolvedQuestion createModelUnsolved() throws IllegalValueException {
        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }
        return new UnsolvedQuestion(question);
    }

    private SolvedQuestion createModelSolved() throws IllegalValueException {
        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }
        if (!SolvedQuestion.isValidSolution(solution)) {
            throw new IllegalValueException(SolvedQuestion.MESSAGE_SOLUTION_CONSTRAINTS);
        }

        return new SolvedQuestion(question, solution);
    }

}
