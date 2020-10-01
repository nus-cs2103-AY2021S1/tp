package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.task.TitleContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TITLE_COOK = "Cook dinner";
    public static final String VALID_TITLE_WASH = "Wash dishes";
    public static final String VALID_PHONE_COOK = "11111111";
    public static final String VALID_PHONE_WASH = "22222222";
    public static final String VALID_EMAIL_COOK = "amy@example.com";
    public static final String VALID_EMAIL_WASH = "bob@example.com";
    public static final String VALID_ADDRESS_COOK = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_WASH = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String TITLE_DESC_COOK = " " + PREFIX_TITLE + VALID_TITLE_COOK;
    public static final String TITLE_DESC_WASH = " " + PREFIX_TITLE + VALID_TITLE_WASH;
    public static final String PHONE_DESC_COOK = " " + PREFIX_PHONE + VALID_PHONE_COOK;
    public static final String PHONE_DESC_WASH = " " + PREFIX_PHONE + VALID_PHONE_WASH;
    public static final String EMAIL_DESC_COOK = " " + PREFIX_EMAIL + VALID_EMAIL_COOK;
    public static final String EMAIL_DESC_WASH = " " + PREFIX_EMAIL + VALID_EMAIL_WASH;
    public static final String ADDRESS_DESC_COOK = " " + PREFIX_ADDRESS + VALID_ADDRESS_COOK;
    public static final String ADDRESS_DESC_WASH = " " + PREFIX_ADDRESS + VALID_ADDRESS_WASH;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "Homework&"; // '&' not allowed in titles
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_COOK;
    public static final EditCommand.EditTaskDescriptor DESC_WASH;

    static {
        DESC_COOK = new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_COOK)
                .withPhone(VALID_PHONE_COOK).withEmail(VALID_EMAIL_COOK).withAddress(VALID_ADDRESS_COOK)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_WASH = new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_WASH)
                .withPhone(VALID_PHONE_WASH).withEmail(VALID_EMAIL_WASH).withAddress(VALID_ADDRESS_WASH)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

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
     * - the address book, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitTitle = task.getTitle().fullTitle.split("\\s+");
        model.updateFilteredTaskList(new TitleContainsKeywordsPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
