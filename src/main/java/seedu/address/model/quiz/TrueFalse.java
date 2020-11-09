package seedu.address.model.quiz;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.model.quiz.exceptions.InvalidQuestionAnswerException;

/**
 * Represents a True/False question. This question has only two valid options: true and false.
 */
public class TrueFalse extends Question {

    public static final ArrayList<String> OPTIONS = new ArrayList<>(Arrays.asList("True", "False"));

    private boolean answer;

    /**
     * Every field must be present and not null.
     */
    public TrueFalse(String prompt, Boolean answer) {
        super(prompt);
        requireNonNull(answer);
        this.answer = answer;
    }

    public boolean getAnswer() {
        return answer;
    }

    @Override
    public Question copy() {
        return new TrueFalse(this.prompt, this.answer);
    }

    /**
     * Returns the question prompt and options, represented in an appropriate String format.
     */
    public String getQuestion() {
        String question = "";
        question += prompt + " ";
        question += "True or False?";
        return question;
    }

    /**
     * Checks whether the attempt is a valid option.
     */
    public boolean isValidResponse(String attempt) {
        String formattedAttempt = attempt.toLowerCase();
        return (formattedAttempt.equals("true")) || (formattedAttempt.equals("false"));
    }

    /**
     * Checks whether the response is correct.
     * @param response user response
     * @return true if the response is correct and false otherwise
     * @throws InvalidQuestionAnswerException if the response is not valid
     */
    @Override
    public boolean checkResponse(String response) throws InvalidQuestionAnswerException {
        if (!isValidResponse(response)) {
            throw new InvalidQuestionAnswerException(Messages.MESSAGE_INVALID_TF_ANSWER);
        }
        String formattedResponse = response.toLowerCase();
        if (formattedResponse.equals("true")) {
            return answer;
        } else {
            return !answer;
        }
    }
    @Override
    public int compareTo(Question f2) {
        return prompt.compareTo(f2.prompt);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof TrueFalse) {
            TrueFalse trueFalse = (TrueFalse) (other);
            return trueFalse.prompt.equals(prompt) && (trueFalse.answer == answer);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prompt, answer);
    }

    @Override
    public String toString() {
        return getQuestion();
    }

    @Override
    public boolean isMcq() {
        return false;
    }
}
