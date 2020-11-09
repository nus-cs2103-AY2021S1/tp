package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_ES2660;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ModuleList;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {
    // Manually added - Modules's details found in {@code CommandTestUtil}
    public static final Module CS2030 = new ModuleBuilder().withName("CS2030")
        .withZoomLink("Lecture", "https://nus-sg.zoom.us/CS2030")
            .withGradePoint(5.0).withTag("completed").build();
    public static final Module CS2101 = new ModuleBuilder().withName("CS2101")
        .withZoomLink("Lecture", "https://nus-sg.zoom.us/CS2101").build();
    public static final Module CS2105 = new ModuleBuilder().withName("CS2105")
        .withZoomLink("Lecture", "https://nus-sg.zoom.us/CS2105").build();
    public static final Module CS1101S = new ModuleBuilder().withName("CS1101S")
        .withZoomLink("Lecture", "https://nus-sg.zoom.us/cS1101S").build();
    public static final Module IS1103 = new ModuleBuilder().withName("IS1103")
        .withZoomLink("Lecture", "https://nus-sg.zoom.us/IS1103").build();

    // Manually added
    public static final Module CS1231S = new ModuleBuilder().withName("CS1231S")
        .withZoomLink("Lecture", "https://nus-sg.zoom.us/cs1231s").build();
    public static final Module MA1101R = new ModuleBuilder().withName("MA1101R")
        .withZoomLink("Lecture", "https://nus-sg.zoom.us/ma1101r").build();

    // Manually added - Modules's details found in {@code CommandTestUtil}
    public static final Module CS2103T = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
        .withZoomLink(VALID_MODULE_LESSON_LECTURE, VALID_ZOOM_LINK_CS2103T).build();
    public static final Module ES2660 = new ModuleBuilder().withName(VALID_MODULENAME_ES2660)
        .withZoomLink(VALID_MODULE_LESSON_TUTORIAL, VALID_ZOOM_LINK_ES2660).build();

    // Modules with assignments
    public static final Module CS2030_WITH_ASSIGNMENT = new ModuleBuilder().withName("CS2030")
            .withAssignment("Quiz 1", 10, 0.8).build();
    public static final Module CS2101_WITH_ASSIGNMENT = new ModuleBuilder().withName("CS2101")
            .withAssignment("Quiz 2", 20, 0.9).build();
    public static final Module CS2105_WITH_ASSIGNMENT = new ModuleBuilder().withName("CS2105")
            .withAssignment("Lab 1", 5, 1).build();

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code ModuleList} with all the typical modules.
     */
    public static ModuleList getTypicalModuleList() {
        ModuleList moduleList = new ModuleList();
        for (Module module : getTypicalModules()) {
            moduleList.addModule(module);
        }
        return moduleList;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2030, CS2101, CS2105, CS1101S, IS1103));
    }

    public static List<Module> getTypicalModulesWithAssignment() {
        return new ArrayList<>(Arrays.asList(CS2030_WITH_ASSIGNMENT, CS2101_WITH_ASSIGNMENT, CS2105_WITH_ASSIGNMENT));
    }

    public static ModuleList getTypicalModulesWithAssignmentList() {
        ModuleList moduleList = new ModuleList();
        for (Module module : getTypicalModulesWithAssignment()) {
            moduleList.addModule(module);
        }
        return moduleList;
    }
}
