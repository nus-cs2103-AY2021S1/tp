package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.setValidCorrectSemester;
import static seedu.address.testutil.TypicalModules.getTypicalGradeBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.GoalTarget;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGradeBook(), new UserPrefs(), new GoalTarget());
    }

    @Test
    public void execute_newModule_success() {
        setValidCorrectSemester();

        Module validModule = new ModuleBuilder().withName("IS1103").build();

        Model expectedModel = new ModelManager(model.getGradeBook(), new UserPrefs(), new GoalTarget());
        expectedModel.addModule(validModule);

        assertCommandSuccess(new AddCommand(validModule), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validModule), expectedModel);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        setValidCorrectSemester();

        Module moduleInList = model.getGradeBook().getModuleList().get(0);
        assertCommandFailure(new AddCommand(moduleInList), model, AddCommand.MESSAGE_DUPLICATE_MODULE);
    }

}
