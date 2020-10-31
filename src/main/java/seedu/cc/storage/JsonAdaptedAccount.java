package seedu.cc.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.model.account.Account;
import seedu.cc.model.account.Name;
import seedu.cc.model.account.entry.ExpenseList;
import seedu.cc.model.account.entry.RevenueList;

/**
 * Jackson-friendly version of {@link Account}.
 */
public class JsonAdaptedAccount {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Account's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedExpense> expenses = new ArrayList<>();
    private final List<JsonAdaptedRevenue> revenues = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAccount} with the given account.
     */
    @JsonCreator
    public JsonAdaptedAccount(@JsonProperty("name") String name,
                              @JsonProperty("expenses") List<JsonAdaptedExpense> expenses,
                              @JsonProperty("revenues") List<JsonAdaptedRevenue> revenues) {
        this.name = name;
        this.expenses.addAll(expenses);
        this.revenues.addAll(revenues);
    }

    /**
     * Converts a given {@code Account} into this class for Jackson use.
     */
    public JsonAdaptedAccount(Account source) {
        name = source.getName().toString();
        expenses.addAll(source.getExpenseList().stream().map(JsonAdaptedExpense::new).collect(Collectors.toList()));
        revenues.addAll(source.getRevenueList().stream().map(JsonAdaptedRevenue::new).collect(Collectors.toList()));
    }
    /**
     * Converts this account into the model's {@code Account} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Account toModelType() throws IllegalValueException {
        ExpenseList accountExpenses = new ExpenseList();
        RevenueList accountProfits = new RevenueList();
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        for (JsonAdaptedRevenue profit : revenues) {
            accountProfits.add(profit.toModelType());
        }

        for (JsonAdaptedExpense expense : expenses) {
            accountExpenses.add(expense.toModelType());
        }

        Account modelAcc = new Account(modelName);
        modelAcc.setExpenses(accountExpenses);
        modelAcc.setRevenues(accountProfits);
        return modelAcc;
    }

}
