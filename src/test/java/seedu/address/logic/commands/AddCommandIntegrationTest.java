package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalTrackr;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.CS2103T;
import static seedu.address.testutil.TypicalTutorialGroups.T05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;
    private Module moduleInView = CS2103T;
    private TutorialGroup tgInView = T05;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTrackr(), new UserPrefs());
    }

    @Test
    public void execute_newModule_success() {
        Module validModule = new ModuleBuilder().withModuleId("MA1521").build();

        Model expectedModel = new ModelManager(model.getModuleList(), new UserPrefs());
        expectedModel.addModule(validModule);

        assertCommandSuccess(new AddModuleCommand(validModule), model,
                String.format(AddModuleCommand.MESSAGE_SUCCESS, validModule), expectedModel);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module moduleInList = model.getModuleList().getList().get(0);
        assertCommandFailure(new AddModuleCommand(moduleInList), model,
                AddModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    //    // todo: tutorial group

    //    @Test
    //    public void execute_newStudent_success() {
    //        Student validStudent = new StudentBuilder().withStudentId("A2037483T").build();
    //
    //        Model expectedModel = new ModelManager(model.getModuleList(), new UserPrefs());
    //        expectedModel.setViewToTutorialGroup(moduleInView);
    //        expectedModel.setViewToStudent(tgInView);
    //        expectedModel.addStudent(validStudent);
    //
    //        model.setViewToTutorialGroup(moduleInView);
    //        model.setViewToStudent(tgInView);
    //        assertCommandSuccess(new AddStudentCommand(validStudent), model,
    //                String.format(AddStudentCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_duplicateStudent_throwsCommandException() {
    //        model.setViewToTutorialGroup(moduleInView);
    //        model.setViewToStudent(tgInView);
    //        Student studentInList = model.getFilteredStudentList().get(0);
    //        assertCommandFailure(new AddStudentCommand(studentInList), model,
    //                AddStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    //    }
}
