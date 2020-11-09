package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_SEARCH_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModuleCodes.CODE_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;

import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleCode;
import seedu.address.nusmods.ModuleInfo;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchCommand}.
 */
public class SearchCommandTest {

    private static final String CS1010X = "src/test/resources/NusmodsDataManagerTest/CS1010X.json";
    private Model model = new ModelManager(getTypicalGradPad(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalGradPad(), new UserPrefs());

    @Test
    public void equals() {
        SearchCommand searchFirstCommand = new SearchCommand(CODE_FIRST_MODULE);
        SearchCommand searchSecondCommand = new SearchCommand(CODE_SECOND_MODULE);

        // same object -> returns true
        assertEquals(searchFirstCommand, searchFirstCommand);

        // same values -> returns true
        SearchCommand searchFirstCommandCopy = new SearchCommand(CODE_FIRST_MODULE);
        assertEquals(searchFirstCommand, searchFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, searchFirstCommand);

        // null -> returns false
        assertNotEquals(null, searchFirstCommand);

        // different module -> returns false
        assertNotEquals(searchFirstCommand, searchSecondCommand);
    }

    @Test
    public void execute_invalidModuleCode_throwsCommandException() {
        ModuleCode invalidModuleCode = new ModuleCode("AA0000");
        SearchCommand searchCommand = new SearchCommand(invalidModuleCode);
        assertCommandFailure(searchCommand, model,
                String.format(Messages.MESSAGE_FAILED_TO_FIND_MODULE, invalidModuleCode.toString()));
    }

    @Test
    public void execute_searchCommand_success() throws DataConversionException {
        ModuleCode moduleCode = new ModuleCode("CS1010X");
        ModuleInfo cs1010x = JsonUtil.readJsonFile(Paths.get(CS1010X), ModuleInfo.class).get();
        SearchCommand command = new SearchCommand(moduleCode);

        String expectedPreclusion = Optional.ofNullable(cs1010x.getPreclusion()).orElse("None");
        String expectedPrerequisite = Optional.ofNullable(cs1010x.getPrerequisite()).orElse("None");

        String expectedMessage = String.format(MESSAGE_SEARCH_SUCCESS,
                cs1010x.getModuleCode(), cs1010x.getModuleCredit(),
                cs1010x.getTitle(), cs1010x.getDescription(),
                expectedPreclusion, expectedPrerequisite, cs1010x.getSemesters());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
