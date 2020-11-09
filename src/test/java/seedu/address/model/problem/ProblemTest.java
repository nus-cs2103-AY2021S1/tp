package seedu.address.model.problem;

import org.junit.jupiter.api.Test;

class ProblemTest {

    @Test
    void isSameProblem() {
        Severity severity = new Severity("high");
        Severity differentSeverity = new Severity("medium");
        Description description = new Description("description");
        Description differentDescription = new Description("different description");
        Problem problem = new Problem(severity, description);
        Problem equivalent = new Problem(severity, description);
        Problem nullProblem = null;
        Problem differentProblem = new Problem(differentSeverity, differentDescription);

        // test the identical problem
        boolean isSame1 = problem.isSameProblem(problem);
        assert isSame1 : "identical problem not detected";

        // test an equivalent problem
        boolean isSame2 = problem.isSameProblem(equivalent);
        assert isSame2 : "equivalent problem not detected";

        // test null input
        boolean isSame3 = problem.isSameProblem(nullProblem);
        assert !isSame3 : "null input mis-detected as same problem";

        // test different problem
        boolean isSame4 = problem.isSameProblem(differentProblem);
        assert !isSame4 : "different problem detected as same";
    }

    @Test
    void getSeverity() {
        // test if returns the same severity as input
        Severity severity = new Severity("high");
        Description description = new Description("description");
        Problem problem = new Problem(severity, description);

        assert severity.equals(problem.getSeverity()) : "does not return the same severity";
    }

    @Test
    void getDescription() {
        // test if returns the same description as input
        Severity severity = new Severity("high");
        Description description = new Description("description");
        Problem problem = new Problem(severity, description);

        assert description.equals(problem.getDescription()) : "does not return the same description";
    }

    @Test
    void testToString() {
        // test if returns the expected string
        Severity severity = new Severity("high");
        Description description = new Description("description");
        Problem problem = new Problem(severity, description);
        String expected = " Description: description Severity: HIGH";
        String result = problem.toString();

        assert expected.equals(result) : "does not return the expected string";
    }
}
