package seedu.address.model.quiz.exceptions;

/**
 * Signals that an answer for a particular question is not a valid option.
 */
public class InvalidQuestionAnswerException extends Exception {

    public InvalidQuestionAnswerException(String mesg) {
        super(mesg);
    }
}
