package seedu.homerce.logic.commands.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_ISFIXED;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.testutil.expense.EditExpenseDescriptorBuilder;
import seedu.homerce.ui.expensepanel.ExpenseListPanel;

/**
 * Contains helper methods for testing expense commands.
 */
public class ExpenseCommandTestUtil {

    public static final String VALID_DESCRIPTION_CONDITIONER = "Conditioner";
    public static final String VALID_DESCRIPTION_AIRCON = "Aircon";
    public static final String VALID_ISFIXED_CONDITIONER = "n";
    public static final String VALID_ISFIXED_AIRCON = "y";
    public static final double VALID_VALUE_CONDITIONER = 15.00;
    public static final double VALID_VALUE_AIRCON = 100.00;
    public static final String VALID_DATE_DECEMBER = "10-12-2020";
    public static final String VALID_DATE_JANUARY = "10-1-2020";
    public static final String VALID_TAG_EQUIPMENT = "Equipment";
    public static final String VALID_TAG_HAIRSUPPLIES = "Hairsupplies";

    public static final String DESC_DESC_CONDITIONER = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CONDITIONER;
    public static final String DESC_DESC_AIRCON = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AIRCON;
    public static final String DESC_ISFIXED_CONDITIONER = " " + PREFIX_ISFIXED + VALID_ISFIXED_CONDITIONER;
    public static final String DESC_ISFIXED_AIRCON = " " + PREFIX_ISFIXED + VALID_ISFIXED_AIRCON;
    public static final String DESC_VALUE_CONDITIONER = " " + PREFIX_AMOUNT + VALID_VALUE_CONDITIONER;
    public static final String DESC_VALUE_AIRCON = " " + PREFIX_AMOUNT + VALID_VALUE_AIRCON;
    public static final String DESC_DATE_DECEMBER = " " + PREFIX_DATE + VALID_DATE_DECEMBER;
    public static final String DESC_DATE_JANUARY = " " + PREFIX_DATE + VALID_DATE_JANUARY;
    public static final String DESC_TAG_EQUIPMENT = " " + PREFIX_TAG + VALID_TAG_EQUIPMENT;
    public static final String DESC_TAG_HAIRSUPPLIES = " " + PREFIX_TAG + VALID_TAG_HAIRSUPPLIES;

    public static final String INVALID_DESC_DESC = " " + PREFIX_DESCRIPTION + "Conditioner&";
    public static final String INVALID_DESC_ISFIXED = " " + PREFIX_ISFIXED + "a";
    public static final String INVALID_DESC_VALUE = " " + PREFIX_AMOUNT + -100.00;
    public static final String INVALID_DESC_DATE = " " + PREFIX_DATE + "30-30-2020";
    public static final String INVALID_DESC_TAG = " " + PREFIX_TAG + "Equipment*";

    public static final String PREAMBLE_WHITESPACE = " ";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditExpenseCommand.EditExpenseDescriptor DESC_CONDITIONER;
    public static final EditExpenseCommand.EditExpenseDescriptor DESC_AIRCON;

    static {
        DESC_CONDITIONER = new EditExpenseDescriptorBuilder().withDescription(VALID_DESCRIPTION_CONDITIONER)
                .withIsFixed(VALID_ISFIXED_CONDITIONER).withAmount(VALID_VALUE_CONDITIONER)
                .withDate(VALID_DATE_DECEMBER).withTag(VALID_TAG_HAIRSUPPLIES).build();
        DESC_AIRCON = new EditExpenseDescriptorBuilder().withDescription(VALID_DESCRIPTION_AIRCON)
                .withIsFixed(VALID_ISFIXED_AIRCON).withAmount(VALID_VALUE_AIRCON)
                .withDate(VALID_DATE_JANUARY).withTag(VALID_TAG_EQUIPMENT).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel, HistoryManager.getInstance());
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
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccessTab(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, ExpenseListPanel.TAB_NAME);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the homerce, filtered expense list and selected expense in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ExpenseTracker expectedExpenseTracker = new ExpenseTracker(actualModel.getExpenseTracker());
        List<Expense> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExpenseList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel,
                HistoryManager.getInstance()));
        assertEquals(expectedExpenseTracker, actualModel.getExpenseTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredExpenseList());
    }
}

