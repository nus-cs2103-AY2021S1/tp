package seedu.address.model.student.academic;

import static java.util.Objects.requireNonNull;

public class Feedback {

    public String feedback;

    public Feedback(String feedback) {
        requireNonNull(feedback);

        this.feedback = feedback;
    }

}
