package seedu.address.storage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Question;

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
        int status = source.isResolved ? 1 : 0;
        this.question = String.format("%1d | %2s", status, source.question);
    }

    public String getQuestion() {
        return question;
    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted detail.
     */
    public Question toModelType() throws IllegalValueException {
        Pattern pattern = Pattern.compile("(?<isResolved>[01])(\\s\\|\\s)(?<question>.*)");
        Matcher matcher = pattern.matcher(question);
        if (!matcher.matches()) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }

        String questionDetail = matcher.group("question");
        if (!Question.isValidQuestion(questionDetail)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }

        boolean status = Integer.parseInt(matcher.group("isResolved")) != 0;
        return new Question(questionDetail, status);
    }
}
