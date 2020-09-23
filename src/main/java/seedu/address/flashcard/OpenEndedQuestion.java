package seedu.address.flashcard;

/**
 * Represents an open ended question.
 */
public class OpenEndedQuestion implements Question{
    final private String question;

    public OpenEndedQuestion(String question) {
        this.question = question;
    }

    @Override
    public String getQuestion() {
        return question;
    }
}
