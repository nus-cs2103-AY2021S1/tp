package seedu.address.testutil;

import seedu.address.model.problem.Description;
import seedu.address.model.problem.Problem;
import seedu.address.model.problem.Severity;

/**
 * A utility class to help with building Book objects.
 */
public class ProblemBuilder {

    public static final String DEFAULT_SEVERITY = "MEDIUM";
    public static final String DEFAULT_DESCRIPTION = "KNOCK KNOCK";

    private Severity severity;
    private Description description;

    /**
     * Creates a {@code ProblemBuilder} with the default details.
     */
    public ProblemBuilder() {
        severity = new Severity(DEFAULT_SEVERITY);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the ProblemBuilder with the data of {@code problemToCopy}.
     */
    public ProblemBuilder(Problem problemToCopy) {
        severity = problemToCopy.getSeverity();
        description = problemToCopy.getDescription();
    }

    /**
     * Sets the {@code Severity} of the {@code Problem} that we are building.
     */
    public ProblemBuilder withSeverity(String severity) {
        this.severity = new Severity(severity);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Problem} that we are building.
     */
    public ProblemBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Problem build() {
        return new Problem(severity, description);
    }
}
