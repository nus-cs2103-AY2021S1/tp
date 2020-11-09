package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_P1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_P2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEVERITY_P1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEVERITY_P2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Library;
import seedu.address.model.problem.Problem;

/**
 * A utility class containing a list of {@code problems} objects to be used in tests.
 */
public class TypicalProblems {
    public static final Problem P1 =
            new ProblemBuilder().withSeverity(VALID_SEVERITY_P1).withDescription(VALID_DESCRIPTION_P1).build();
    public static final Problem P2 =
            new ProblemBuilder().withSeverity(VALID_SEVERITY_P2).withDescription(VALID_DESCRIPTION_P2).build();

    /**
     * Returns an {@code Library} with all the typical problems.
     */
    public static Library getTypicalReportLibrary() {
        Library lib = new Library();
        for (Problem problem : getTypicalReport()) {
            lib.addProblem(problem);
        }
        return lib;
    }

    public static List<Problem> getTypicalReport() {
        return new ArrayList<>(Arrays.asList(P1, P2));
    }
}
