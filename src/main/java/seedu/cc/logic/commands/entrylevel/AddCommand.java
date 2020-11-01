package seedu.cc.logic.commands.entrylevel;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_AMOUNT;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_TAG;
import static seedu.cc.model.account.ActiveAccount.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.cc.model.account.ActiveAccount.PREDICATE_SHOW_ALL_REVENUE;

import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.entry.Entry;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.model.account.entry.Revenue;

/**
 * Adds a entry to Common Cents.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an entry (revenue or expense) to "
            + "Common Cents\n"
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_AMOUNT + "AMOUNT "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "revenue "
            + PREFIX_DESCRIPTION + "Sale of clothes "
            + PREFIX_AMOUNT + "200 "
            + PREFIX_TAG + "blogshop "
            + PREFIX_TAG + "eCommerce";

    public static final String PREFIXES = PREFIX_CATEGORY + "CATEGORY\n" + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + PREFIX_AMOUNT + "AMOUNT";
    public static final String MESSAGE_SUCCESS = "New entry added!\n(%1$s)";

    private final Entry entry;

    /**
     * Creates an AddCommand to add the specified {@code Entry}
     */
    public AddCommand(Entry entry) {
        requireNonNull(entry);
        this.entry = entry;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);
        // Set previous state for undo before new entry is added
        activeAccount.setPreviousState();


        if (entry instanceof Expense) {
            activeAccount.addExpense((Expense) entry);
        } else {
            assert entry instanceof Revenue;
            activeAccount.addRevenue((Revenue) entry);
        }

        activeAccount.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        activeAccount.updateFilteredRevenueList(PREDICATE_SHOW_ALL_REVENUE);
        model.setAccount(activeAccount.getAccount());
        return CommandResultFactory
            .createCommandResultForEntryListChangingCommand(String.format(MESSAGE_SUCCESS, this.entry));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && entry.equals(((AddCommand) other).entry));
    }
}
