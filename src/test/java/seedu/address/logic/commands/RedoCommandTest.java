package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.UserHistoryManager;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.patient.Patient;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.PatientBuilder;


public class RedoCommandTest {

    private static final String SAMPLE_COMMAND =
            "p-add n/John Doe g/MALE bd/2018-12-27 bt/A+ p/98765432 e/johnd@example.com a/311,"
                    + " Clementi Ave 2, #02-25 t/friends t/owesMoney";
    private static final String SAMPLE_COMMAND_2 =
            "p-add n/Kim Guan g/MALE bd/2018-12-27 bt/A+ p/98765432 e/johnd@example.com a/311,"
                    + " Clementi Ave 2, #02-25 t/friends t/owesMoney";

    @TempDir
    public Path testFolder;
    private StorageManager storageManager;
    private Model model = new ModelManager();
    private Logic logic;
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
     * Test basic undo functionality
     * @throws CommandException
     */
    @Test
    public void testRedo() throws CommandException, ParseException {
        CommandResult commandResultOne = logic.execute(SAMPLE_COMMAND);
        int originalSize = model.getAddressBook().getPatientList().size();
        model.undoHistory();
        model.redoHistory();
        int newSize = model.getAddressBook().getPatientList().size();

        assertNotEquals(originalSize, newSize - 1);
    }

    /**
     * Test undo after running multiple commands
     * @throws CommandException
     */
    @Test
    public void testConsecutiveCommandFollowedByRedo() throws CommandException, ParseException {
        CommandResult commandResultOne = logic.execute(SAMPLE_COMMAND);
        CommandResult commandResultTwo = logic.execute(SAMPLE_COMMAND_2);
        int originalSize = model.getAddressBook().getPatientList().size();
        CommandResult undoCommand = new UndoCommand().execute(model);
        CommandResult redoCommand = new RedoCommand().execute(model);
        int newSize = model.getAddressBook().getPatientList().size();

        assertEquals(originalSize, newSize);
    }

    /**
     * Test multiple undo after running multiple commands
     * @throws CommandException
     */
    @Test
    public void testConsecutiveCommandFollowedByConsecutiveRedo() throws CommandException, ParseException {
        Patient validPatientOne = new PatientBuilder().build();
        CommandResult commandResultOne = logic.execute(SAMPLE_COMMAND);
        CommandResult commandResultTwo = logic.execute(SAMPLE_COMMAND_2);
        int originalSize = model.getAddressBook().getPatientList().size();
        CommandResult undoCommand = new UndoCommand().execute(model);
        CommandResult undoCommandTwo = new UndoCommand().execute(model);
        CommandResult redoCommand = new RedoCommand().execute(model);
        CommandResult redoCommandTwo = new RedoCommand().execute(model);
        int newSize = model.getAddressBook().getPatientList().size();

        assertEquals(originalSize, newSize);
    }

    /**
     * Test multiple undo after a single command
     * @throws CommandException
     */
    @Test
    public void testMultipleRedoAfterSingleUndo() throws CommandException, ParseException {
        CommandResult commandResultOne = logic.execute(SAMPLE_COMMAND);

        try {
            CommandResult undoCommand = new UndoCommand().execute(model);
            CommandResult redoCommand = new RedoCommand().execute(model);
            CommandResult redoCommandTwo = new RedoCommand().execute(model);
        } catch (CommandException e) {
            assertEquals(model.getUserHistoryManager().getUserHistorySize(), 2);
        }
    }
}
