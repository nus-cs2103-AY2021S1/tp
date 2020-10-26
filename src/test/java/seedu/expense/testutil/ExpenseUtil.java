package seedu.expense.testutil;

import static seedu.expense.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.expense.logic.commands.AddCommand;
import seedu.expense.logic.commands.EditCommand.EditExpenseDescriptor;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.tag.Tag;

/**
 * A utility class for Expense.
 */
public class ExpenseUtil {

    /**
     * Returns an add command string for adding the {@code expense}.
     */
    public static String getAddCommand(Expense expense) {
        return AddCommand.COMMAND_WORD + " " + getExpenseDetails(expense);
    }

    /**
     * Returns the part of command string for the given {@code expense}'s details.
     */
    public static String getExpenseDetails(Expense expense) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DESCRIPTION + expense.getDescription().fullDescription + " ");
        sb.append(PREFIX_AMOUNT + expense.getAmount().toString() + " ");
        sb.append(PREFIX_DATE + expense.getDate().toString() + " ");
        sb.append(PREFIX_TAG + expense.getTag().tagName);
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditExpenseDescriptor}'s details.
     */
    public static String getEditExpenseDescriptorDetails(EditExpenseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.fullDescription).append(" "));
        descriptor.getAmount().ifPresent(amount -> sb.append(PREFIX_AMOUNT).append(amount.toString()).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.toString()).append(" "));
        if (descriptor.getTag().isPresent()) {
            Tag tag = descriptor.getTag().get();
            sb.append(PREFIX_TAG).append(tag.tagName).append(" ");
        }
        return sb.toString();
    }
}
