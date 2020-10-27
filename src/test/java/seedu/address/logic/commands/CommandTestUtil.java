package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_TAG_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagNameContainsKeywordsPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TAG_NAME_CS2103 = "cs2103";
    public static final String VALID_TAG_NAME_CS2101 = "cs2101";
    public static final String INVALID_TAG_NAME = "&";
    public static final String USER_DIRECTORY_ADDRESS = System.getProperty("user.dir");
    public static final String VALID_FILE_ADDRESS_CS2103 = "./src/test/java/seedu/address/testutil/cs2103.bat";
    public static final String VALID_FILE_ADDRESS_CS2101 = "./src/test/java/seedu/address/testutil/cs2101.bat";
    public static final String VALID_MAC_FILE_ADDRESS_CS2101 = "./src/test/java/seedu/address/testutil/cs2101.sh";
    public static final String VALID_MAC_FILE_ADDRESS_TESTFILE = "./src/test/java/seedu/address/testutil/testFile.sh";
    public static final String VALID_LABEL = "testLabel";
    public static final String INVALID_LABEL = "@label";

    public static final String OLD_TAG_DESC_CS2101 = " " + PREFIX_OLD_TAG_NAME + VALID_TAG_NAME_CS2101;
    public static final String OLD_TAG_DESC_CS2103 = " " + PREFIX_OLD_TAG_NAME + VALID_TAG_NAME_CS2103;
    public static final String TAG_DESC_CS2103 = " " + PREFIX_TAG_NAME + VALID_TAG_NAME_CS2103;
    public static final String TAG_DESC_CS2101 = " " + PREFIX_TAG_NAME + VALID_TAG_NAME_CS2101;
    public static final String VALID_LABEL_DESC = " " + PREFIX_LABEL_NAME + VALID_LABEL;


    public static final String FILE_ADDRESS_DESC_CS2103 = " " + PREFIX_FILE_ADDRESS + VALID_FILE_ADDRESS_CS2103;
    public static final String FILE_ADDRESS_DESC_CS2101 = " " + PREFIX_FILE_ADDRESS + VALID_FILE_ADDRESS_CS2101;

    public static final String INVALID_OLD_TAG_DESC = " " + PREFIX_OLD_TAG_NAME + INVALID_TAG_NAME;
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG_NAME + INVALID_TAG_NAME;
    public static final String INVALID_LABEL_DESC = " " + PREFIX_LABEL_NAME + INVALID_LABEL;
    // empty string not allowed for addresses
    public static final String INVALID_FILE_ADDRESS_DESC = " " + PREFIX_FILE_ADDRESS + " ";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
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
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered tag list and selected tag in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Tag> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTagList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredTagList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTagAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTagList().size());

        Tag person = model.getFilteredTagList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getTagName().tagName.split("\\s+");
        model.updateFilteredTagList(new TagNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTagList().size());
    }

}
