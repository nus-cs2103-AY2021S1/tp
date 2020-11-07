package seedu.address.testutil;

import static seedu.address.testutil.TypicalStudents.getTypicalStudents;
import static seedu.address.testutil.TypicalTutorialGroups.T05;
import static seedu.address.testutil.TypicalTutorialGroups.getTutorialGroupList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Trackr;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class TypicalModulesPopulatedWithTutorialGroups {

    public static final Module CS2100 = new Module(new ModuleId("CS2100"));
    public static final Module CS2103T = new Module(new ModuleId("CS2103T"));
    public static final Module CS2040 = new Module(new ModuleId("CS2040"));
    public static final Module CS2030 = new Module(new ModuleId("CS2030"));

    /**
     * Returns an {@code Trackr} with all the typical modules.
     */
    public static Trackr getTypicalModuleList() {
        Trackr tr = new Trackr();
        for (Module module: getTypicalModules()) {
            for (TutorialGroup tutorialGroup : TypicalTutorialGroups.getTutorialGroupList()) {
                if (!module.getUniqueTutorialGroupList().contains(tutorialGroup)) {
                    module.addTutorialGroup(tutorialGroup);
                }
            }
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
        for (Module module : getTypicalModules()) {
            trackr.addModule(module);
        }
        for (TutorialGroup tutorialGroup : getTutorialGroupList()) {
            if (!CS2103T.getUniqueTutorialGroupList().contains(tutorialGroup)) {
                trackr.addTutorialGroup(tutorialGroup, CS2103T);
            }
        }
        for (Student student : getTypicalStudents()) {
            if (!T05.getUniqueStudentList().contains(student)) {
                trackr.addStudent(CS2103T, T05, student);
            }
        }
        return trackr;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2100, CS2103T, CS2040));
    }
}
