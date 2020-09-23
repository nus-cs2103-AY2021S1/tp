package seedu.address.flashcard;

/**
 * Represents a mcq question.
 * Options for the mcq will be stored in an array while the
 * question itself will be stored as a {@code String}.
 */
public class MCQ implements Question{
    final private String[] options;
    final String question;

    public MCQ(String question, String... options) {
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
}
