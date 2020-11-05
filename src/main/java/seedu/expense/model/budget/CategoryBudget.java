package seedu.expense.model.budget;

import seedu.expense.model.expense.Amount;
import seedu.expense.model.tag.Tag;

/**
 * Represents a category-budget in the expense book.
 */
public class CategoryBudget implements Budget {
    private Amount amount;
    private Tag tag;

    /**
     * Constructs a new category-budget with the specified {@code Tag} with zero amount.
     * @param tag
     */
    public CategoryBudget(Tag tag) {
        amount = new Amount(0);
        this.tag = tag;
    }

    public Amount getAmount() {
        return amount;
    }

    public Tag getTag() {
        return tag;
    }

    /**
     * Tops up the budget by the specified {@code Amount}.
     */
    public void topupBudget(Amount toAdd) {
        assert toAdd.greaterThanEquals(Amount.zeroAmount());
        amount = amount.add(toAdd);
    }

    /**
     * Resets the amount in the {@code budget} to have zero value.
     */
    public void reset() {
        amount = new Amount(0);
    }

    /**
     * Sets the amount in the default category-budget to be the same as {@code toCopy}
     */
    public void copyAmount(Amount toCopy) {
        reset();
        topupBudget(toCopy);
    }

    /**
     * Returns true if both category-budgets have the same {@code Tag}.
     * This defines a weaker notion of equality between the two category-budgets.
     */
    public boolean isSameCategoryBudget(CategoryBudget otherCategoryBudget) {
        if (otherCategoryBudget == this) {
            return true;
        }
        return otherCategoryBudget != null && otherCategoryBudget.getTag().equals(tag);
    }

    @Override
    public String toString() {
        return String.format("Budget: %s", amount.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CategoryBudget)) {
            return false;
        }

        CategoryBudget otherCategoryBudget = (CategoryBudget) other;
        return otherCategoryBudget.getTag().equals(getTag());
    }
}
