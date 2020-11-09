package seedu.address.model.problem;

import java.util.Objects;

public class Problem {
    private final Severity severity;
    private final Description description;

    /**
     * Creates a problem object.
     * @param severity severity of object.
     * @param description description of object.
     */
    public Problem(Severity severity, Description description) {
        this.severity = severity;
        this.description = description;
    }

    /**
     * Returns true if both books of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two books.
     */
    public boolean isSameProblem(Problem otherProblem) {
        if (otherProblem == this) {
            return true;
        }


        return otherProblem != null
                && otherProblem.getDescription().equals(getDescription())
                && (otherProblem.getSeverity().equals(getSeverity()));
    }

    public Severity getSeverity() {
        return severity;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(severity, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Description: ")
                .append(getDescription())
                .append(" Severity: ")
                .append(getSeverity());
        return builder.toString();
    }
}
