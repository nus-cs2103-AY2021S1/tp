package seedu.address.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;


public class UserHistoryManagerTest {
    private static final String SAMPLE_COMMAND =
            "p-add n/John Doe g/MALE bd/2018-12-27 bt/A+ p/98765432 e/johnd@example.com a/311, "
                    + "Clementi"
                    + " Ave 2, #02-25 t/friends t/owesMoney";
    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    private Model model = new ModelManager();
    private LogicManager logic;
    private UserHistoryManager historyManager;

    /**
     * Initialises a dummy ModelManager to aid with testing
     */
    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storageManager);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    /**
     * Test if the program starts its previously stored data in its history
     */
    @Test
    public void testInitialiseWithPreviousHistory() {
        assertEquals(model.getUserHistoryManager().getUserHistorySize(), 1);
    }

    @Test
    public void testAddHistory() throws CommandException, ParseException {
        CommandResult commandResultOne = logic.execute(SAMPLE_COMMAND);
        System.out.println(model.getUserHistoryManager().getUserHistorySize());
        int size = model.getUserHistoryManager().getUserHistorySize();
        assertEquals(size, 2);
    }

    @Test
    public void testUndoHistory() throws CommandException, ParseException {
        CommandResult commandResultOne = logic.execute(SAMPLE_COMMAND);
        int sizeBefore = model.getUserHistoryManager().getUserHistorySize();
        model.getUserHistoryManager().undoHistory();
        int sizeAfter = model.getUserHistoryManager().getUserHistorySize();
        assertEquals(sizeBefore, 2);
        assertEquals(sizeBefore - 1, sizeAfter);
    }

    @Test
    public void testRedoHistory() throws CommandException, ParseException {
        CommandResult commandResultOne = logic.execute(SAMPLE_COMMAND);
        int sizeBefore = model.getUserHistoryManager().getUserHistorySize();
        model.getUserHistoryManager().undoHistory();
        model.getUserHistoryManager().redoHistory();
        int sizeAfter = model.getUserHistoryManager().getUserHistorySize();
        assertEquals(sizeBefore, 2);
        assertEquals(sizeBefore , sizeAfter);
    }


    @Test
    public void getHistoryTest() {
        assertEquals(model.getUserHistoryManager().getHistory().get(0).getKey(),
                model.getAddressBook().getPatientList());
        assertEquals(model.getUserHistoryManager().getHistory().get(0).getValue(),
                model.getAddressBook().getAppointmentList());
    }
}
