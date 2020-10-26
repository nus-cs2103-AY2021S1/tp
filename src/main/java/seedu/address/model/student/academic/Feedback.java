package seedu.address.model.student.academic;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Feedback {

    public static final String MESSAGE_CONSTRAINTS = "Feedback should be alphanumeric";
    public static final String VALIDATION_REGEX = "^[\\s]*[a-zA-Z0-9][a-zA-Z0-9\\s]*$";

    public String feedback;

    public Feedback(String feedback) {
        requireNonNull(feedback);
        checkArgument(isValidFeedback(feedback), MESSAGE_CONSTRAINTS);

        this.feedback = feedback;
    }

    /**
     * Returns true if a given string is a valid feedback.
     */
    public static boolean isValidFeedback(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
