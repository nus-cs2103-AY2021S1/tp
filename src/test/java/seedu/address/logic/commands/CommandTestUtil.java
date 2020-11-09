package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorManager;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_BOB = "66666666";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel, new StorageManager());
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, Storage actualStorage,
                                            CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel, actualStorage);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, Storage, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, Storage actualStorage,
                                            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualStorage, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered vendor list and selected vendor in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        VendorManager expectedVendorManager = new VendorManager(actualModel.getVendorManager());
        List<Vendor> expectedFilteredList = new ArrayList<>(actualModel.getObservableVendorList());

        assertThrows(CommandException.class, expectedMessage, () ->
                command.execute(actualModel, new StorageManager()));
        assertEquals(expectedVendorManager, actualModel.getVendorManager());
        assertEquals(expectedFilteredList, actualModel.getObservableVendorList());
    }
    //    /**
    //     * Updates {@code model}'s filtered list to show only the vendor at the given {@code targetIndex} in the
    //     * {@code model}'s address book.
    //     */
    //    public static void showVendorAtIndex(Model model, Index targetIndex) {
    //        assertTrue(targetIndex.getZeroBased() < model.getFilteredVendorList().size());
    //
    //        Vendor vendor = model.getFilteredVendorList().get(targetIndex.getZeroBased());
    //        final String[] splitName = vendor.getName().fullName.split("\\s+");
    //        model.updateFilteredVendorList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
    //
    //        assertEquals(1, model.getFilteredVendorList().size());
    //    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered vendor list and selected vendor in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, Storage actualStorage,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        VendorManager expectedVendorManager = new VendorManager(actualModel.getVendorManager());
        List<Vendor> expectedFilteredList = new ArrayList<>(actualModel.getObservableVendorList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel, actualStorage));
        assertEquals(expectedVendorManager, actualModel.getVendorManager());
        assertEquals(expectedFilteredList, actualModel.getObservableVendorList());
    }

}
