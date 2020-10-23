package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_CATEGORY;

import seedu.address.commons.core.category.Category;
import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;

public class GetTotalCommand extends Command {

    public static final String COMMAND_WORD = "total";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Gets the total value of all the expenses or revenues.\n"
        + "Parameters: "
        + PREFIX_CATEGORY + "CATEGORY\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_CATEGORY + "expense";

    public static final String MESSAGE_SUCCESS = "Total %1$s" + "s: $";

    private final Category category;

    /**
     * Creates a GetTotalCommand to get the total expenses or revenues
     * @param category
     */
    public GetTotalCommand(Category category) {
        requireNonNull(category);
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);

        double totalSum;
        if (category.isExpense()) {
            totalSum = activeAccount.getTotalExpenses();
        } else {
            assert category.isRevenue();
            totalSum = activeAccount.getTotalRevenue();
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, category)
            + String.format("%.2f", totalSum));
    }
}
