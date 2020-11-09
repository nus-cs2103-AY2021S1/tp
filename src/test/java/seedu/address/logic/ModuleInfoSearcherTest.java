package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.nusmods.ModuleInfo;
import seedu.address.nusmods.NusmodsDataManager;
import seedu.address.nusmods.NusmodsDataManagerTest;

/**
 * Contains integration tests for {@code ModuleInfoSearcher}.
 */
public class ModuleInfoSearcherTest {

    private static final String CS1010X = "src/test/resources/NusmodsDataManagerTest/CS1010X.json";

    private ModuleInfoSearcher moduleInfoSearcher = new ModuleInfoSearcher();

    @Test
    public void search_empty_module() {
        String emptyModule = "";
        assertThrows(CommandException.class, Messages.MESSAGE_EMPTY_SEARCH, ()
            -> moduleInfoSearcher.searchModule(emptyModule));
    }

    @Test
    public void search_false_module() {
        String falseModule = "AA0000";
        assertThrows(CommandException.class, String.format(Messages.MESSAGE_FAILED_TO_FIND_MODULE,
                falseModule), () -> moduleInfoSearcher.searchModule(falseModule));
    }

    @Test
    public void search_success() throws DataConversionException {
        ModuleInfo cs1010x = JsonUtil.readJsonFile(Paths.get(CS1010X), ModuleInfo.class).get();
        String moduleCode = "CS1010X";
        ModuleInfo actualModuleInfo = null;
        try {
            actualModuleInfo = moduleInfoSearcher.searchModule(moduleCode);
        } catch (CommandException e) {
            assert false;
        }
        assertEquals(actualModuleInfo, cs1010x);
    }

    @Test
    public void search_failed_throwsNusmodsException() {
        NusmodsDataManager stubManager = NusmodsDataManagerTest.getStubManager();
        ModuleInfoSearcher moduleInfoSearcherStub = new ModuleInfoSearcher(stubManager);
        assertThrows(CommandException.class, () -> moduleInfoSearcherStub.searchModule("CS2103T"));
    }

    public static class ModuleInfoSearcherStub extends ModuleInfoSearcher {
        @Override
        public ModuleInfo searchModule(String moduleCode) throws CommandException {
            throw new CommandException("stub exception message");
        }
    }
}
