package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_NAME_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_NAME_B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.GradeBook;
import seedu.address.model.module.Module;
import seedu.address.model.semester.Semester;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module COM_ORG = new ModuleBuilder().withName("CS2100")
            .withGrade("A-")
            .withSemester(Semester.Y2S1.toString()).build();
    public static final Module EFF_COM = new ModuleBuilder().withName("CS2101")
            .withGrade("B-")
            .withSemester(Semester.Y2S1.toString()).build();
    public static final Module SWE = new ModuleBuilder().withName("CS2103T")
            .withGrade("A+")
            .withSemester(Semester.Y2S2.toString()).build();
    public static final Module COM_INFO = new ModuleBuilder().withName("ES2660")
            .withGrade("C")
            .withSemester(Semester.Y3S1.toString()).build();
    public static final Module ASK_QN = new ModuleBuilder().withName("GEQ1000")
            .withGrade("C+").build();
    public static final Module STATS = new ModuleBuilder().withName("ST2334")
            .withGrade("B").build();
    public static final Module ALGO = new ModuleBuilder().withName("CS2040S")
            .withGrade("B+").build();

    // Manually added
    public static final Module GEH = new ModuleBuilder().withName("GEH1036")
            .withGrade("A").build();
    public static final Module GER = new ModuleBuilder().withName("GER1000")
            .withGrade("A").build();

    // Manually added - Module's details found in {@code CommandTestUtil}
    public static final Module MOD_A = new ModuleBuilder().withName(VALID_MOD_NAME_A)
            .withGrade(VALID_GRADE_A).build();
    public static final Module MOD_B = new ModuleBuilder().withName(VALID_MOD_NAME_B)
            .withGrade(VALID_GRADE_B)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalModules() {
    } // prevents instantiation

    /**
     * Returns an {@code GradeBook} with all the typical modules.
     */
    public static GradeBook getTypicalGradeBook() {
        GradeBook ab = new GradeBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(COM_ORG, EFF_COM, SWE, COM_INFO, ASK_QN, STATS, ALGO));
    }
}
