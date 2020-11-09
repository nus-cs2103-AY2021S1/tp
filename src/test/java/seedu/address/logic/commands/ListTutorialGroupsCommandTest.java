package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.testutil.TutorialGroupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListTutorialGroupsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        Module firstModule = getTypicalModuleList().getList().get(0);
        firstModule.addTutorialGroup(new TutorialGroupBuilder().build());
        model = new ModelManager(getTypicalModuleList(), new UserPrefs());
        expectedModel = new ModelManager(model.getModuleList(), new UserPrefs());
        model.setViewToTutorialGroup(firstModule);
        expectedModel.setViewToTutorialGroup(firstModule);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() throws CommandException {
        Module firstModule = getTypicalModuleList().getList().get(0);
        new ListTutorialGroupCommand().execute(model);
        assertCommandSuccess(new ListTutorialGroupCommand(), model,
            String.format(ListTutorialGroupCommand.MESSAGE_VIEWING_TUTORIALGROUPS_SUCCESS, firstModule), expectedModel);
    }
}
