package seedu.expense.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.expense.commons.exceptions.IllegalValueException;
import seedu.expense.model.ExpenseBook;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.expense.Expense;

/**
 * An Immutable ExpenseBook that is serializable to JSON format.
 */
@JsonRootName(value = "expensebook")
class JsonSerializableExpenseBook {

    public static final String MESSAGE_DUPLICATE_EXPENSE = "Expenses list contains duplicate expense(s).";

    private final List<JsonAdaptedExpense> expenses = new ArrayList<>();
    private final JsonAdaptedBudgetList budgets;

    /**
     * Constructs a {@code JsonSerializableExpenseBook} with the given expenses and budget.
     */
    @JsonCreator
    public JsonSerializableExpenseBook(@JsonProperty("expenses") List<JsonAdaptedExpense> expenses,
                                       @JsonProperty("budget") JsonAdaptedBudgetList budgets) {
        this.expenses.addAll(expenses);
        this.budgets = budgets;
    }

    /**
     * Converts a given {@code ReadOnlyExpenseBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableExpenseBook}.
     */
    public JsonSerializableExpenseBook(ReadOnlyExpenseBook source) {
        expenses.addAll(source.getExpenseList().stream().map(JsonAdaptedExpense::new).collect(Collectors.toList()));
        budgets = new JsonAdaptedBudgetList(source.getBudgets());
    }

    /**
     * Converts this expense book into the model's {@code ExpenseBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ExpenseBook toModelType() throws IllegalValueException {
        ExpenseBook expenseBook = new ExpenseBook();
        for (JsonAdaptedExpense jsonAdaptedExpense : expenses) {
            Expense expense = jsonAdaptedExpense.toModelType();
            if (expenseBook.hasExpense(expense)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EXPENSE);
            }
            expenseBook.addExpense(expense);
        }
        expenseBook.setBudgets(budgets.toModelType());
        return expenseBook;
    }

}
