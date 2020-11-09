package seedu.address.model.quiz;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.model.quiz.exceptions.InvalidQuestionAnswerException;

/**
 * Represents a MCQ question. It is assumed that the options are numerically indexed, starting from 1.
 */
public class Mcq extends Question {

    private int answer;
    private List<String> options;

    /**
     * Every field must be present and not null.
     */
    public Mcq(String prompt, Integer answer, List<String> options) {
        super(prompt);
        requireAllNonNull(prompt, answer, options);
        this.answer = answer;
        this.options = options;
        assert(isValidResponse(answer));
    }

    public int getAnswer() {
        return answer;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public Question copy() {
        return new Mcq(this.prompt, this.answer, this.options);
    }

    /**
     * Returns the question prompt and options, represented in an appropriate String format.
     */
    public String getQuestion() {
        String question = "";
        question += prompt + " Options: ";
        for (int i = 0; i < options.size(); i++) {
            question += Integer.toString(i + 1) + ")";
            question += options.get(i);
            if (i < options.size() - 1) {
                question += ", ";
            }
        }
        return question;
    }

    /**
     * Checks whether the attempt is a valid option.
     */
    public boolean isValidResponse(int attempt) {
        return (attempt >= 1) && (attempt <= options.size());
    }

    /**
     * Checks whether the response is correct.
     * @param response user response
     * @return true if the response is correct and false otherwise
     * @throws InvalidQuestionAnswerException if the response is not valid
     */
    @Override
    public boolean checkResponse(String response) throws InvalidQuestionAnswerException {
        try {
            int attempt = Integer.parseInt(response);
            if (!isValidResponse(attempt)) {
                throw new InvalidQuestionAnswerException(Messages.MESSAGE_INVALID_MCQ_ANSWER);
            }
            return attempt == answer;
        } catch (NumberFormatException e) {
            throw new InvalidQuestionAnswerException(Messages.MESSAGE_INVALID_MCQ_ANSWER);
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
        if (other instanceof Mcq) {
            Mcq mcq = (Mcq) (other);
            boolean isEqual = (mcq.prompt.equals(prompt)) && (mcq.answer == answer);
            for (int i = 0; i < options.size(); i++) {
                if (!options.get(i).equals(mcq.options.get(i))) {
                    isEqual = false;
                }
            }
            return isEqual;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prompt, answer, options);
    }

    @Override
    public String toString() {
        return getQuestion();
    }

    @Override
    public boolean isMcq() {
        return true;
    }
}
