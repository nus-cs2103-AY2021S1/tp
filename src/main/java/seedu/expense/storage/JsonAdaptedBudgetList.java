package seedu.expense.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.expense.commons.exceptions.IllegalValueException;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.budget.UniqueCategoryBudgetList;

/**
 * Jackson-friendly version of {@link CategoryBudget}.
 */
public class JsonAdaptedBudgetList {

    public static final String MESSAGE_DUPLICATE_CATEGORY_BUDGET = "Budgets list contains duplicate"
            + " category-budget(s).";

    private final JsonAdaptedBudget defaultCategory;
    private final List<JsonAdaptedBudget> categoryBudgets = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedBudget} with the given amount.
     */
    @JsonCreator
    public JsonAdaptedBudgetList(@JsonProperty("defaultCategory") JsonAdaptedBudget defaultCategory,
                                 @JsonProperty("categoryBudgets") List<JsonAdaptedBudget> categoryBudgets) {
        this.defaultCategory = defaultCategory;
        this.categoryBudgets.addAll(categoryBudgets);
    }

    /**
     * Converts a given {@code Budget} into this class for Jackson use.
     */
    public JsonAdaptedBudgetList(UniqueCategoryBudgetList budgets) {
        defaultCategory = new JsonAdaptedBudget(budgets.getDefaultCategory());
        this.categoryBudgets.addAll(budgets.getCategoryBudgets().stream()
                .map(JsonAdaptedBudget::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted budget object into the model's {@code Budget} object.
     */
    public UniqueCategoryBudgetList toModelType() throws IllegalValueException {
        UniqueCategoryBudgetList budgetList = new UniqueCategoryBudgetList();
        budgetList.topupBudget(defaultCategory.toModelType().getAmount());
        for (JsonAdaptedBudget jsonAdaptedBudget : categoryBudgets) {
            CategoryBudget categoryBudget = jsonAdaptedBudget.toModelType();
            if (budgetList.contains(categoryBudget)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CATEGORY_BUDGET);
            }
            budgetList.add(categoryBudget);
        }
        return budgetList;
    }
}
