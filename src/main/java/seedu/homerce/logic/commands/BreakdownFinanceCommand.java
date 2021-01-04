package seedu.homerce.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_MONTH_OF_YEAR;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_YEAR;

import java.time.Month;
import java.time.Year;
import java.util.function.Predicate;

import seedu.homerce.model.Model;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.expense.predicate.ExpenseMonthYearPredicate;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.revenue.predicate.RevenueMonthYearPredicate;

public class BreakdownFinanceCommand extends Command {

    public static final String COMMAND_WORD = "breakdownfinance";
    public static final String MESSAGE_SUCCESS = "Finances breakdown";
    public static final String MESSAGE_USAGE = "Displays the breakdown of finances for the month "
            + "and year specified \n"
            + "Parameters: "
            + PREFIX_MONTH_OF_YEAR + "MONTH "
            + PREFIX_YEAR + "YEAR \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MONTH_OF_YEAR + "12"
            + PREFIX_YEAR + "2020";

    private final Month month;
    private final Year year;

    /**
     * Breaks down expenses by Tag Name.
     */
    public BreakdownFinanceCommand(Month month, Year year) {
        this.month = month;
        this.year = year;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);
        Predicate<Expense> expensePredicate = new ExpenseMonthYearPredicate(month, year);
        Predicate<Revenue> revenuePredicate = new RevenueMonthYearPredicate(month, year);
        model.updateFilteredExpenseList(expensePredicate);
        model.updateFilteredRevenueList(revenuePredicate);

        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
