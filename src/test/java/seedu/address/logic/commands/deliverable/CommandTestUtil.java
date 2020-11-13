package seedu.address.logic.commands.deliverable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_CONTACTS;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_MILESTONE;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.deliverable.DeliverableBook;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.model.deliverable.deliverable.TitleDescriptionContainsKeywordsPredicate;
import seedu.address.testutil.EditDeliverableDescriptorBuilder;

public class CommandTestUtil {

    public static final String VALID_TITLE_A = "Login Page";
    public static final String VALID_TITLE_B = "Profile Page";
    public static final String VALID_MILESTONE_A = "1.2.1";
    public static final String VALID_MILESTONE_B = "3.1";
    public static final String VALID_DESCRIPTION_A = "Include email, username and password fields";
    public static final String VALID_DESCRIPTION_B = "Able to see archived posts";
    public static final String VALID_DEADLINE_A = "12-06-2020 14:00";
    public static final String VALID_DEADLINE_B = "12-10-2020 16:00";
    public static final String VALID_CONTACTS_A = "Alice, Bob, Sarah";
    public static final String VALID_CONTACTS_B = "Alice   Alice,    Bob    Bob, Sarah Sarah";

    public static final String TITLE_DESC_A = " " + PREFIX_TITLE + VALID_TITLE_A;
    public static final String TITLE_DESC_B = " " + PREFIX_TITLE + VALID_TITLE_B;
    public static final String MILESTONE_DESC_A = " " + PREFIX_MILESTONE + VALID_MILESTONE_A;
    public static final String MILESTONE_DESC_B = " " + PREFIX_MILESTONE + VALID_MILESTONE_B;
    public static final String DESCRIPTION_DESC_A = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_A;
    public static final String DESCRIPTION_DESC_B = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_B;
    public static final String DEADLINE_DESC_A = " " + PREFIX_DEADLINE + VALID_DEADLINE_A;
    public static final String DEADLINE_DESC_B = " " + PREFIX_DEADLINE + VALID_DEADLINE_B;
    public static final String CONTACTS_DESC_A = " " + PREFIX_CONTACTS + VALID_CONTACTS_A;
    public static final String CONTACTS_DESC_B = " " + PREFIX_CONTACTS + VALID_CONTACTS_B;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "Email & Password login"; // '&' not allowed
    public static final String INVALID_MILESTONE_DESC = " " + PREFIX_MILESTONE + "1,2,3"; // commas not allowed
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + ""; // empty desc not allowed
    public static final String INVALID_BY_DESC = " " + PREFIX_DEADLINE + "35-10-2020 19:00"; // invalid datetime
    public static final String INVALID_CONTACTS_DESC = " " + PREFIX_CONTACTS + "abc"; // alphabets not allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditDeliverableDescriptor DESC_DELIVERABLE_A;
    public static final EditCommand.EditDeliverableDescriptor DESC_DELIVERABLE_B;

    static {
        DESC_DELIVERABLE_A = new EditDeliverableDescriptorBuilder().withTitle(VALID_TITLE_A)
                .withMilestone(VALID_MILESTONE_A).withDescription(VALID_DESCRIPTION_A)
                .withDeadline(VALID_DEADLINE_A).withContacts(VALID_CONTACTS_A).build();
        DESC_DELIVERABLE_B = new EditDeliverableDescriptorBuilder().withTitle(VALID_TITLE_B)
                .withMilestone(VALID_MILESTONE_B).withDescription(VALID_DESCRIPTION_B)
                .withDeadline(VALID_DEADLINE_B).withContacts(VALID_CONTACTS_B).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(
            Command command, ModelDeliverable actualModelDeliverable,
            CommandResult expectedCommandResult, ModelDeliverable expectedModelDeliverable) {
        try {
            CommandResult result = command.execute(actualModelDeliverable);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModelDeliverable, actualModelDeliverable);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, ModelDeliverable, CommandResult, ModelDeliverable)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(
            Command command, ModelDeliverable actualModelDeliverable,
            String expectedMessage, ModelDeliverable expectedModelDeliverable) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModelDeliverable, expectedCommandResult, expectedModelDeliverable);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the deliverable book, filtered deliverable list and selected deliverable in {@code actualModel} remain
     * unchanged
     */
    public static void assertCommandFailure(
            Command command, ModelDeliverable actualModelDeliverable, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        DeliverableBook expectedDeliverableBook = new DeliverableBook(actualModelDeliverable.getDeliverableBook());
        List<Deliverable> expectedFilteredList = new ArrayList<>(actualModelDeliverable.getFilteredDeliverableList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModelDeliverable));
        assertEquals(expectedDeliverableBook, actualModelDeliverable.getDeliverableBook());
        assertEquals(expectedFilteredList, actualModelDeliverable.getFilteredDeliverableList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the deliverable at the given {@code targetIndex} in the
     * {@code model}'s deliverable book.
     */
    public static void showDeliverableAtIndex(ModelDeliverable modelDeliverable, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < modelDeliverable.getFilteredDeliverableList().size());

        Deliverable deliverable = modelDeliverable.getFilteredDeliverableList().get(targetIndex.getZeroBased());
        final String[] splitName = deliverable.getTitle().value.split("\\s+");
        modelDeliverable.updateFilteredDeliverableList(
                new TitleDescriptionContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, modelDeliverable.getFilteredDeliverableList().size());
    }
}
