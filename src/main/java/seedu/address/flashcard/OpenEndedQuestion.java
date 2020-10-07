package seedu.address.flashcard;

import java.util.Optional;

/**
 * Represents an open ended question.
 */
public class OpenEndedQuestion implements Question {

    public static final String TYPE = "OEQ";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String MESSAGE_CONSTRAINTS = "OpenEndedQuestion can take any values, "
            + "and it should not be blank";

    private final String question;

    public OpenEndedQuestion(String question) {
        this.question = question;
    }

    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String getQuestion() {
        return question;
        //List<String> questionArray = new ArrayList<String>();
        //questionArray.add(question);
        //return questionArray;
    }

    @Override
    public String getFormatQuestion() {
        return question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof OpenEndedQuestion) {
            OpenEndedQuestion temp = (OpenEndedQuestion) o;
            return this.toString().equals(temp.toString());
        }
        return false;
    }

    public Optional<Choice[]> getChoices() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return question;
    }
}
