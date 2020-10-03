package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.account.Account;
import seedu.address.model.account.Name;
import seedu.address.model.account.entry.ExpenseList;
import seedu.address.model.account.entry.ProfitList;

/**
 * Jackson-friendly version of {@link Account}.
 */
public class JsonAdaptedAccount {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Account's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedExpense> expenses = new ArrayList<>();
    private final List<JsonAdaptedProfit> profits = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAccount} with the given account.
     */
    @JsonCreator
    public JsonAdaptedAccount(@JsonProperty("name") String name,
                              @JsonProperty("expenses") List<JsonAdaptedExpense> expenses,
                              @JsonProperty("profits") List<JsonAdaptedProfit> profits) {
        this.name = name;
        this.expenses.addAll(expenses);
        this.profits.addAll(profits);
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedAccount(Account source) {
        name = source.getName().toString();
        expenses.addAll(source.getExpenseList().stream().map(JsonAdaptedExpense::new).collect(Collectors.toList()));
        profits.addAll(source.getProfitList().stream().map(JsonAdaptedProfit::new).collect(Collectors.toList()));
    }
    /**
     * Converts this account into the model's {@code Account} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Account toModelType() throws IllegalValueException {
        ExpenseList accountExpenses = new ExpenseList();
        ProfitList accountProfits = new ProfitList();

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        for (JsonAdaptedProfit profit : profits) {
            accountProfits.add(profit.toModelType());
        }

        for (JsonAdaptedExpense expense : expenses) {
            accountExpenses.add(expense.toModelType());
        }

        Account modelAcc = new Account(modelName);
        modelAcc.setExpenses(accountExpenses);
        modelAcc.setProfits(accountProfits);
        return modelAcc;
    }

}
