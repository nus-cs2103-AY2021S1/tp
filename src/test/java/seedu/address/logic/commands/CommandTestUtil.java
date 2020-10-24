package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CommonCents;
import seedu.address.model.Model;
import seedu.address.model.account.Account;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.ActiveAccountManager;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_CATEGORY_EXPENSE = "expense";
    public static final String VALID_CATEGORY_REVENUE = "revenue";
    public static final String VALID_DESCRIPTION_EXPENSE = "buying flower seeds";
    public static final String VALID_DESCRIPTION_REVENUE = "selling flowers";
    public static final String VALID_AMOUNT_EXPENSE = "15.65";
    public static final String VALID_AMOUNT_REVENUE = "32.99";
    public static final String VALID_TAG_ROSES = "roses";
    public static final String VALID_TAG_SUNFLOWER = "sunflowers";

    public static final String CATEGORY_DESC_EXPENSE = " " + PREFIX_CATEGORY + VALID_CATEGORY_EXPENSE;
    public static final String CATEGORY_DESC_REVENUE = " " + PREFIX_CATEGORY + VALID_CATEGORY_REVENUE;
    public static final String DESCRIPTION_DESC_EXPENSE = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_EXPENSE;
    public static final String DESCRIPTION_DESC_REVENUE = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_REVENUE;
    public static final String AMOUNT_DESC_EXPENSE = " " + PREFIX_AMOUNT + VALID_AMOUNT_EXPENSE;
    public static final String AMOUNT_DESC_REVENUE = " " + PREFIX_AMOUNT + VALID_AMOUNT_REVENUE;
    public static final String TAG_DESC_ROSES = " " + PREFIX_TAG + VALID_TAG_ROSES;
    public static final String TAG_DESC_SUNFLOWERS = " " + PREFIX_TAG + VALID_TAG_SUNFLOWER;

    public static final String INVALID_CATEGORY_DESC = " "
            + PREFIX_CATEGORY + "revenu"; // only 'revenue' or 'expense' allowed
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + " "; // description cannot be empty
    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "0"; // amount cannot be 0 in value
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    private static final int GENERAL_ACC_INDEX = 0;

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandResult expectedCommandResult, Model expectedModel) {
        ActiveAccount activeAccount =
                new ActiveAccountManager(actualModel.getFilteredAccountList().get(GENERAL_ACC_INDEX));
        try {
            CommandResult result = command.execute(actualModel, activeAccount);
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
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = CommandResultFactory.createDefaultCommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the common cents, filtered account list and selected account in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ActiveAccount activeAccount =
                new ActiveAccountManager(actualModel.getFilteredAccountList().get(GENERAL_ACC_INDEX));
        CommonCents expectedCommonCents = new CommonCents(actualModel.getCommonCents());
        List<Account> expectedFilteredList = new ArrayList<>(actualModel.getFilteredAccountList());
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel, activeAccount));
        assertEquals(expectedCommonCents, actualModel.getCommonCents());
        assertEquals(expectedFilteredList, actualModel.getFilteredAccountList());
    }

}
