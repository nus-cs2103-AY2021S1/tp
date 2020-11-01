package trackitnus.logic.commands.module;

import static trackitnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackitnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackitnus.testutil.typical.TypicalTrackIter.getTypicalTrackIter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.model.Model;
import trackitnus.model.ModelManager;
import trackitnus.model.UserPrefs;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.module.Module;

/**
 * Contains integration tests (interaction with the Model) for {@code AddModuleCommand}.
 */
public class AddModuleCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTrackIter(), new UserPrefs());
    }

    @Test
    public void execute_newModule_success() {
        Module validModule = new Module(new Code("CS2103T"), new Name("Sample"));

        Model expectedModel = new ModelManager(model.getTrackIter(), new UserPrefs());
        expectedModel.addModule(validModule);

        assertCommandSuccess(new AddModuleCommand(validModule), model,
            String.format(AddModuleCommand.MESSAGE_ADD_MODULE_SUCCESS, validModule), expectedModel);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module moduleInList = model.getTrackIter().getModuleList().get(0);
        assertCommandFailure(new AddModuleCommand(moduleInList), model, Messages.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleCode_throwsCommandException() {
        Module validModule = new Module(new Code("CS1231S"), new Name("Sample"));
        assertCommandFailure(new AddModuleCommand(validModule), model, Messages.MESSAGE_DUPLICATE_MODULE);
    }

}
