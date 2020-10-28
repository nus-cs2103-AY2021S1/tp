package seedu.address.testutil;

import static seedu.address.logic.parser.util.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_KEYWORDS;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import seedu.address.logic.commands.AddAccountCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAccountCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditAccountNameCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.SwitchAccountCommand;
import seedu.address.model.account.entry.Entry;
import seedu.address.model.account.entry.Revenue;

/**
 * A utility class for Expense/Revenue.
 */
public class EntryUtil {
    public static final String EXPENSE_STRING = "expense";
    public static final String REVENUE_STRING = "revenue";

    /**
     * Returns an add command string for adding the {@code entry}.
     */
    public static String getAddCommand(Entry entry) {
        return AddCommand.COMMAND_WORD + " " + getEntryDetails(entry);
    }

    /**
     * Returns a delete command string for deleting first expense.
     */
    public static String getDeleteExpenseCommand() {
        return DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ENTRY.getOneBased()
                + " " + PREFIX_CATEGORY + EXPENSE_STRING;
    }

    /**
     * Returns a delete command string for deleting first revenue.
     */
    public static String getDeleteRevenueCommand() {
        return DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ENTRY.getOneBased()
                + " " + PREFIX_CATEGORY + REVENUE_STRING;
    }

    /**
     * Returns an edit command string for editing first expense.
     */
    public static String getValidEditExpenseCommand() {
        // replace the info of the 1st entry in the revenue list with that of PAY_RENT
        return EditCommand.COMMAND_WORD + " " + INDEX_FIRST_ENTRY.getOneBased()
            + " " + getEntryDetails(TypicalEntries.PAY_RENT) + " t/";
    }

    /**
     * Returns an edit command string for editing first revenue.
     */
    public static String getValidEditRevenueCommand() {
        // replace the info of the 1st entry in the revenue list with that of SELL_FLOWER_SEEDS
        return EditCommand.COMMAND_WORD + " " + INDEX_FIRST_ENTRY.getOneBased()
            + " " + getEntryDetails(TypicalEntries.SELL_FLOWER_SEEDS);
    }

    /**
     * Returns a find command string for finding in expense list.
     */
    public static String getValidFindExpenseCommand() {
        return FindCommand.COMMAND_WORD + " " + PREFIX_CATEGORY + EXPENSE_STRING
            + " " + PREFIX_KEYWORDS + " " + "flowers food";
    }

    /**
     * Returns a find command string for finding in revenue list.
     */
    public static String getValidFindRevenueCommand() {
        return FindCommand.COMMAND_WORD + " " + PREFIX_CATEGORY + REVENUE_STRING
            + " " + PREFIX_KEYWORDS + " " + "canvas";
    }

    /**
     * Returns a find command string without category.
     */
    public static String getValidFindGeneralCommand() {
        return FindCommand.COMMAND_WORD + " " + PREFIX_KEYWORDS + "rent flowers";
    }

    /**
     * Returns a clear expense command string.
     */
    public static String getValidClearExpenseCommand() {
        return ClearCommand.COMMAND_WORD + " " + PREFIX_CATEGORY + EXPENSE_STRING;
    }

    /**
     * Returns a clear revenue command string.
     */
    public static String getValidClearRevenueCommand() {
        return ClearCommand.COMMAND_WORD + " " + PREFIX_CATEGORY + REVENUE_STRING;
    }

    /**
     * Returns an add account command string.
     */
    public static String getValidAddAccountCommand() {
        return AddAccountCommand.COMMAND_WORD + " " + PREFIX_NAME + "newacc";
    }

    /**
     * Returns an edit account command string.
     */
    public static String getValidEditAccountCommand() {
        return EditAccountNameCommand.COMMAND_WORD + " " + PREFIX_NAME + "anotheracc";
    }

    /**
     * Returns an delete account command string.
     */
    public static String getValidDeleteAccountCommand() {
        return DeleteAccountCommand.COMMAND_WORD + " " + INDEX_FIRST_ENTRY.getOneBased();
    }

    /**
     * Returns an switch account command string.
     */
    public static String getValidSwitchAccountCommand() {
        return SwitchAccountCommand.COMMAND_WORD + " " + INDEX_FIRST_ENTRY.getOneBased();
    }

    /**
     * Returns the part of command string for the given {@code entry}'s details.
     */
    public static String getEntryDetails(Entry entry) {
        StringBuilder sb = new StringBuilder();
        boolean isRevenue = entry instanceof Revenue;
        String category = isRevenue
                ? "revenue"
                : "expense";
        sb.append(PREFIX_CATEGORY + category + " ");
        sb.append(PREFIX_DESCRIPTION + entry.getDescription().toString() + " ");
        sb.append(PREFIX_AMOUNT + entry.getAmount().toString() + " ");
        entry.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

}
