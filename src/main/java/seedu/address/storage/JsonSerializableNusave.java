package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Nusave;
import seedu.address.model.ReadOnlyNusave;
import seedu.address.model.budget.Budget;

/**
 * Jackson-friendly version of {@link Nusave}.
 */
public class JsonSerializableNusave {
    private final List<JsonAdaptedBudget> budgets = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableNusave} with the given budgets.
     */
    @JsonCreator
    public JsonSerializableNusave(@JsonProperty("budgets") List<JsonAdaptedBudget> budgets) {
        this.budgets.addAll(budgets);
    }

    /**
     * Converts a given {@code ReadOnlyNusave} into this class for Jackson use.
     * @param source future changes to this will not affect the created {@code JsonSerializableNusave}.
     */
    public JsonSerializableNusave(ReadOnlyNusave source) {
        budgets.addAll(source.getBudgetListAsObservableList().stream().map(JsonAdaptedBudget::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Nusave into the model's {@code Nusave} object.
     * @return the Nusave object converted from Json format.
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Nusave toModelType() throws IllegalValueException {
        Nusave nusave = new Nusave();
        for (JsonAdaptedBudget jsonAdaptedBudget : budgets) {
            Budget budget = jsonAdaptedBudget.toModelType();
            nusave.addBudget(budget);
        }
        return nusave;
    }
}
