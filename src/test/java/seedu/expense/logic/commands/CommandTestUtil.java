package seedu.expense.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.expense.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.expense.commons.core.index.Index;
import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.ExpenseBook;
import seedu.expense.model.Model;
import seedu.expense.model.expense.DescriptionContainsKeywordsPredicate;
import seedu.expense.model.expense.Expense;
import seedu.expense.testutil.EditExpenseDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_DESCRIPTION_FOOD = "Caifan lunch";
    public static final String VALID_DESCRIPTION_BUS = "Coach ride to Malacca";
    public static final String VALID_DESCRIPTION_MISC = "Miscellaneous";
    public static final String VALID_AMOUNT_FOOD = "3.80";
    public static final String VALID_AMOUNT_BUS = "63";
    public static final String VALID_AMOUNT_MISC = "4.20";
    public static final String VALID_DATE_FOOD = "04-10-2020";
    public static final String VALID_DATE_BUS = "09-11-2020";
    public static final String VALID_DATE_MISC = "15-12-2020";
    public static final String VALID_REMARK_FOOD = "The usual: Steamed egg, ladies finger, sweet n sour pork.";
    public static final String VALID_REMARK_BUS = "We're going on a trip??";
    public static final String VALID_TAG_FOOD = "Food";
    public static final String VALID_TAG_TRANSPORT = "Transport";

    public static final String DESCRIPTION_DESC_FOOD = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_FOOD;
    public static final String DESCRIPTION_DESC_BUS = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BUS;
    public static final String DESCRIPTION_DESC_MISC = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MISC;
    public static final String AMOUNT_DESC_FOOD = " " + PREFIX_AMOUNT + VALID_AMOUNT_FOOD;
    public static final String AMOUNT_DESC_BUS = " " + PREFIX_AMOUNT + VALID_AMOUNT_BUS;
    public static final String AMOUNT_DESC_MISC = " " + PREFIX_AMOUNT + VALID_AMOUNT_MISC;
    public static final String DATE_DESC_FOOD = " " + PREFIX_DATE + VALID_DATE_FOOD;
    public static final String DATE_DESC_BUS = " " + PREFIX_DATE + VALID_DATE_BUS;
    public static final String TAG_DESC_FOOD = " " + PREFIX_TAG + VALID_TAG_FOOD;
    public static final String TAG_DESC_TRANSPORT = " " + PREFIX_TAG + VALID_TAG_TRANSPORT;

    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + " "; // empty string not allowed
    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "11a"; // 'a' not allowed in phones
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "24 Jun 2020"; // not in dd-MM-yyyy format
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "friend*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditExpenseDescriptor DESC_FOOD;
    public static final EditCommand.EditExpenseDescriptor DESC_BUS;

    static {
        DESC_FOOD = new EditExpenseDescriptorBuilder().withDescription(VALID_DESCRIPTION_FOOD)
                .withAmount(VALID_AMOUNT_FOOD).withDate(VALID_DATE_FOOD)
                .withTag(VALID_TAG_FOOD).build();
        DESC_BUS = new EditExpenseDescriptorBuilder().withDescription(VALID_DESCRIPTION_BUS)
                .withAmount(VALID_AMOUNT_BUS).withDate(VALID_DATE_BUS)
                .withTag(VALID_TAG_TRANSPORT).build();
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
     * - the expense book, filtered expense list and selected expense in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ExpenseBook expectedExpenseBook = new ExpenseBook(actualModel.getExpenseBook());
        List<Expense> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExpenseList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedExpenseBook, actualModel.getExpenseBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredExpenseList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the expense at the given {@code targetIndex} in the
     * {@code model}'s expense book.
     */
    public static void showExpenseAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredExpenseList().size());

        Expense expense = model.getFilteredExpenseList().get(targetIndex.getZeroBased());
        final String[] splitName = expense.getDescription().fullDescription.split("\\s+");
        model.updateFilteredExpenseList(new DescriptionContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredExpenseList().size());
    }

}
