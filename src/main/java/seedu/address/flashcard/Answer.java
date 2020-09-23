package seedu.address.flashcard;

/**
 * Represents an Answer in a flashcard.
 */
public class Answer {

    public static final String MESSAGE_CONSTRAINTS = "Answer can take any values, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String answer;

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

    /**
     * Returns if a given string is a valid answer.
     */
    public static boolean isValidAnswer(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        } else if(o instanceof Answer) {
            Answer temp = (Answer) o;
            return temp.toString().equals(o.toString());
        }
        return false;
    }
}
