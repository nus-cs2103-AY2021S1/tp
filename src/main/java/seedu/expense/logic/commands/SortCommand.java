package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_SORT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.Model;
import seedu.expense.model.expense.AmountComparator;
import seedu.expense.model.expense.DateComparator;
import seedu.expense.model.expense.DescriptionComparator;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.SortKeyComparator;


/**
 * Sorts the current expenses listed.
 * <p>
 * Able to take in up to 3 parameters, specifying sorts according to Amount, Date and Description.
 * </p>
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String REVERSE_KEYWORD = "R";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts expenses currently listed in Bamboo. "
            + "Parameters: "
            + "[" + PREFIX_SORT + DescriptionComparator.SORT_KEYWORD + "[" + REVERSE_KEYWORD + "]] "
            + "[" + PREFIX_SORT + DateComparator.SORT_KEYWORD + "[" + REVERSE_KEYWORD + "]] "
            + "[" + PREFIX_SORT + AmountComparator.SORT_KEYWORD + "[" + REVERSE_KEYWORD + "]]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT + "date "
            + PREFIX_SORT + "descriptionR ";

    public static final String MESSAGE_SUCCESS = "Expenses sorted according to: %s";

    private Comparator<Expense> expenseComparator;
    private List<String> sortOrder;

    /**
     * Constructor for SortCommand, takes in parameters specifying the sort required.
     * @param sortKeyComparators various comparators that will be merged into 1 in order of their index.
     */
    public SortCommand(SortKeyComparator ... sortKeyComparators) {
        requireNonNull(sortKeyComparators);
        sortOrder = new ArrayList<>();
        List<SortKeyComparator> sortedSortKeyComparators = new ArrayList<>();
        for (SortKeyComparator sortKeyComparator : sortKeyComparators) {
            sortedSortKeyComparators.add(sortKeyComparator);
        }
        sortedSortKeyComparators.sort(Comparator.comparingInt(SortKeyComparator::getSortIndex));

        for (int i = 0; i < sortedSortKeyComparators.size(); i++) {
            SortKeyComparator current = sortedSortKeyComparators.get(i);
            if (current.isActive()) {
                if (expenseComparator == null) {
                    expenseComparator = current.isReverse() ? current.reversed() : current;
                } else {
                    expenseComparator = current.isReverse()
                            ? expenseComparator.thenComparing(current.reversed())
                            : expenseComparator.thenComparing(current);
                }
                sortOrder.add(current.toString());
            }
        }
        assert expenseComparator != null : "ExpenseComparator for SortCommand processed by Parser but is null.";
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.sortExpenseList(expenseComparator);

        return new CommandResult(String.format(MESSAGE_SUCCESS, sortOrder.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand); // instanceof handles nulls
                // && expenseComparator.equals(((SortCommand) other).expenseComparator)); // state check
    }
}
