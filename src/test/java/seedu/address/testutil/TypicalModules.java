package seedu.address.testutil;

import static seedu.address.testutil.TypicalStudents.getTypicalStudents;
import static seedu.address.testutil.TypicalTutorialGroups.getTutorialGroupList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Trackr;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;
import seedu.address.model.person.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {
    public static final Module CS2100 = new Module(new ModuleId("CS2100"));
    public static final Module CS2103T = new Module(new ModuleId("CS2103T"));
    public static final Module CS2040 = new Module(new ModuleId("CS2040"));

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static Trackr getTypicalModuleList() {
        Trackr tr = new Trackr();
        for (Module module: getTypicalModules()) {
            tr.addModule(module);
        }
        return tr;
    }

    /**
     * Returns an {@code Trackr} with all the typical modules, tutorial groups and students.
     */
    public static Trackr getTypicalTrackr() {
        Trackr trackr = new Trackr();
        // populate modules with the same tutorial groups and students
        for (Student student : getTypicalStudents()) {
            for (TutorialGroup tutorialGroup: getTutorialGroupList()) {
                for (Module module : getTypicalModules()) {
                    trackr.addModule(module);
                    trackr.addTutorialGroup(tutorialGroup, module);
                    trackr.addStudent(module, tutorialGroup, student);
                }
            }
        }
        return trackr;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2100, CS2103T, CS2040));
    }
}
