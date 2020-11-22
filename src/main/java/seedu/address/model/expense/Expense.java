package seedu.address.model.expense;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Expense in the expense book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Expense {
    // Identity fields
    private final Amount amount;
    private final Date date;
    private final Category category;
    private final Description description;
    private final Currency dollarSign;

    /**
     * Every field must be present and not null.
     */
    public Expense(Amount amount, Date date, Category category, Description description) {
        requireAllNonNull(amount, date, category);
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
        this.dollarSign = new Currency();
    }

    /**
     * Every field must be present and not null.
     */
    public Expense(Amount amount, Currency dollarSign, Date date, Category category, Description description) {
        requireAllNonNull(amount, date, category);
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
        this.dollarSign = dollarSign;
    }

    public Amount getAmount() {
        return this.amount;
    }

    public Date getDate() {
        return this.date;
    }

    public Category getCategory() {
        return this.category;
    }

    public Description getDescription() {
        return this.description;
    }

    public Currency getCurrency() {
        return this.dollarSign;
    }

    /**
     * Exchange the expense to input currency.
     * @param dollarSign Currency of the input.
     * @param exchangeRate from current currency to input Currency.
     * @return a new expense in the new Currency.
     */
    public Expense exchange(Currency dollarSign, ExchangeRate exchangeRate) {
        return new Expense(
            new Amount(amount.getValue() * exchangeRate.getRate()), dollarSign, date, category, description);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameExpense(Expense otherExpense) {
        if (otherExpense == this) {
            return true;
        }

        return otherExpense != null
                && otherExpense.getAmount().equals(this.getAmount())
                && otherExpense.getDate().equals(this.getDate())
                && otherExpense.getCategory().equals(this.getCategory())
                && otherExpense.getDescription().equals(this.getDescription());
    }

    /**
     * Returns true if both expenses have the same identity and data fields.
     * This defines a stronger notion of equality between two expenses.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Expense)) {
            return false;
        }

        Expense otherExpense = (Expense) other;
        return otherExpense.getAmount().equals(getAmount())
                && otherExpense.getDate().equals(getDate())
                && otherExpense.getCategory().equals(getCategory())
                && otherExpense.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(amount, date, category, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (description.isEmpty()) {
            builder.append(" Amount: ")
                    .append(this.getAmount())
                    .append(this.dollarSign)
                    .append("\n")
                    .append(" Date: ")
                    .append(this.getDate())
                    .append("\n")
                    .append(" Category: ")
                    .append(this.getCategory())
                    .append("\n");
        } else {
            builder.append(" Amount: ")
                    .append(this.getAmount())
                    .append(this.dollarSign)
                    .append("\n")
                    .append(" Date: ")
                    .append(this.getDate())
                    .append("\n")
                    .append(" Category: ")
                    .append(this.getCategory())
                    .append("\n")
                    .append(" Description: ")
                    .append(this.getDescription())
                    .append("\n");
        }
        return builder.toString();
    }
}
