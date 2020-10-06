package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.budget.Budget;
import seedu.address.model.expense.Amount;

/**
 * Jackson-friendly version of {@link Budget}.
 */
public class JsonAdaptedBudget {

    private final String amount;

    /**
     * Constructs a {@code JsonAdaptedBudget} with the given amount.
     */
    @JsonCreator
    public JsonAdaptedBudget(@JsonProperty("amount") String amount) {
        this.amount = amount;
    }

    /**
     * Converts a given {@code Budget} into this class for Jackson use.
     */
    public JsonAdaptedBudget(Budget budget) {
        amount = budget.getAmount().toString();
    }

    /**
     * Converts this Jackson-friendly adapted budget object into the model's {@code Budget} object.
     */
    public Budget toModelType() {
        Budget budget = new Budget();
        budget.topupBudget(new Amount(amount));
        return budget;
    }
}
