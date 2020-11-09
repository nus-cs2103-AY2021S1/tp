package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_ADD_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGradPad(), new UserPrefs());
    }

    @Test
    public void execute_newModule_success() {
        Module validModule = new ModuleBuilder().build();

        Model expectedModel = new ModelManager(model.getGradPad(), new UserPrefs());
        expectedModel.addModule(validModule);

        assertCommandSuccess(new AddCommand(validModule), model,
                String.format(MESSAGE_ADD_SUCCESS, validModule), expectedModel);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module moduleInList = model.getGradPad().getModuleList().get(0);
        assertCommandFailure(new AddCommand(moduleInList), model,
            String.format(MESSAGE_DUPLICATE_MODULE, moduleInList.getModuleCode()));
    }

}
