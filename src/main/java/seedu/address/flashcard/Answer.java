package seedu.address.flashcard;

/**
 * Represents an Answer in a flashcard.
 */
public class Answer {
    final private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    /**
     * Checks the given userAnswer with the correct answer.
     * This is done by strictly comparing lower case string equality.
     * @param userAnswer the user's answer.
     * @return true if the user's answer is equal to the actual answer.
     */
    public boolean checkAnswer(String userAnswer) {
        String lowerCaseActualAnswer = answer.toLowerCase();
        String lowerCaseUserAnswer = userAnswer.toLowerCase();
        return lowerCaseActualAnswer.equals(lowerCaseUserAnswer);
    }

    /**
     * Gets the correct answer.
     * @return the correct answer.
     */
    public String getAnswer() {
        return answer;
    }
}
