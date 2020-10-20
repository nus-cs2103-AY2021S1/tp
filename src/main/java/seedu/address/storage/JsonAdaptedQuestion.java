package seedu.address.storage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;
import seedu.address.model.student.question.UnsolvedQuestion;

/**
 * Jackson-friendly version of {@code Question}.
 */
public class JsonAdaptedQuestion {

    private final String question;

    /**
     * Constructs a {@code JsonAdaptedQuestion} with the given {@code question}.
     */
    @JsonCreator
    public JsonAdaptedQuestion(String question) {
        this.question = question;
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     */
    public JsonAdaptedQuestion(Question source) {
        if (source.isResolved()) {
            SolvedQuestion solved = (SolvedQuestion) source;
            this.question = String.format("1 | %1s | %2s", solved.question, solved.solution);
        } else {
            this.question = String.format("0 | %1s", source.question);
        }
    }

    @JsonValue
    public String getQuestion() {
        return question;
    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted detail.
     */
    public Question toModelType() throws IllegalValueException {
        Pattern pattern = Pattern.compile("(?<isResolved>[01])(\\s\\|\\s)(?<detail>.*)");
        Matcher matcher = pattern.matcher(question);
        if (!matcher.matches()) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }

        boolean status = Integer.parseInt(matcher.group("isResolved")) != 0;

        String questionDetail = matcher.group("detail");
        if (status) {
            return createModelSolved(questionDetail);
        } else {
            return createModelUnsolved(questionDetail);
        }

    }

    private UnsolvedQuestion createModelUnsolved(String questionDetail) throws IllegalValueException {
        if (!Question.isValidQuestion(questionDetail)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }
        return new UnsolvedQuestion(questionDetail);
    }

    private SolvedQuestion createModelSolved(String questionDetail) throws IllegalValueException {
        Pattern pattern = Pattern.compile("(?<question>.*)(\\s\\|\\s)(?<solution>.*)");
        Matcher matcher = pattern.matcher(questionDetail);
        if (!matcher.matches()) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }

        String question = matcher.group("question");
        String solution = matcher.group("solution");
        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }
        if (!SolvedQuestion.isValidSolution(solution)) {
            throw new IllegalValueException(SolvedQuestion.MESSAGE_SOLUTION_CONSTRAINTS);
        }

        return new SolvedQuestion(question, solution);
    }

}
