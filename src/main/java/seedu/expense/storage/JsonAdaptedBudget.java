package seedu.expense.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.expense.commons.exceptions.IllegalValueException;
import seedu.expense.model.budget.Budget;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.expense.Amount;

/**
 * Jackson-friendly version of {@link Budget}.
 */
public class JsonAdaptedBudget {

    private final JsonAdaptedTag tag;
    private final String amount;

    /**
     * Constructs a {@code JsonAdaptedBudget} with the given amount.
     */
    @JsonCreator
    public JsonAdaptedBudget(@JsonProperty("tag") JsonAdaptedTag tag, @JsonProperty("amount") String amount) {
        this.tag = tag;
        this.amount = amount;
    }

    /**
     * Converts a given {@code Budget} into this class for Jackson use.
     */
    public JsonAdaptedBudget(CategoryBudget categoryBudget) {
        tag = new JsonAdaptedTag(categoryBudget.getTag());
        amount = categoryBudget.getAmount().toString();
    }

    /**
     * Converts this Jackson-friendly adapted budget object into the model's {@code Budget} object.
     */
    public CategoryBudget toModelType() throws IllegalValueException {
        CategoryBudget categoryBudget = new CategoryBudget(tag.toModelType());
        categoryBudget.topupBudget(new Amount(amount));
        return categoryBudget;
    }
}
