package seedu.address.flashcard;

/**
 * Represents a mcq question.
 * Options for the mcq will be stored in an array while the
 * question itself will be stored as a {@code String}.
 */
public class Mcq implements Question {

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String MESSAGE_CONSTRAINTS = "MultipleChoiceQuestion can take any values, "
            + "and it should not be blank";

    private final String[] options;
    private final String question;

    /**
     * A constructor to create MCQ Question object.
     */
    public Mcq(String question, String... options) {
        this.question = question;
        this.options = options;
    }

    @Override
    public String getQuestion() {
        StringBuilder sb = new StringBuilder(question + "\n");
        int i = 1;
        for (String option : options) {
            sb.append(i).append(". ").append(option).append("\n");
            i++;
        }
        return sb.toString();
    }

    public String getOnlyQuestion() {
        StringBuilder sb = new StringBuilder(question);
        return sb.toString();
    }

    public String[] getChoices() {
        return this.options;
    }

    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Mcq) {
            Mcq temp = (Mcq) o;
            return temp.getQuestion().equals(getQuestion());
        }
        return false;
    }
}
