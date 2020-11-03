package seedu.expense.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.util.AppUtil.checkArgument;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an Expense's amount in the expense book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount implements Comparable<Amount> {

    public static final String MESSAGE_CONSTRAINTS =
            "Expense Amount restrictions:\n"
            + "Amount Format: should only contain numbers and 1 '.', and should be in the <dollars>.<cents> format.\n"
            + "    \".<cents>\" input is optional but <dollars> should contain at least 1 digit.\n"
            + "Value Restrictions: 0 to 10e9 inclusive";
    public static final String VALIDATION_REGEX = "(?<dollars>\\d+)(.(?<cents>\\d{1,2}))?";
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
    private static final BigDecimal MAX_VALUE = new BigDecimal("10e9");
    private static final BigDecimal MIN_VALUE = new BigDecimal("0");

    // value in Amount stored as cents
    protected final BigDecimal value;

    /**
     * Constructs a {@code Amount}.
     *
     * @param amount A valid amount, as a {@code String}.
     */
    public Amount(String amount) throws IllegalArgumentException {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);

        value = toCents(new BigDecimal(amount));
    }

    /**
     * Constructs a {@code Amount}
     *
     * @param amount A valid amount, as a {@code double}.
     */
    public Amount(double amount) {
        this(String.valueOf(amount));
    }

    /**
     * Constructor for Amount that already comes in cents.
     * @param minTermAmount value already comes in cents.
     */
    public Amount(BigDecimal minTermAmount) {
        requireNonNull(minTermAmount);
        this.value = minTermAmount;
    }

    /**
     * Converts an amount (rounded to two decimal places) into cents.
     */
    private static BigDecimal toCents(BigDecimal amount) {
        return amount.multiply(new BigDecimal("100"));
    }

    /**
     * Converts an amount (in cents) into the format [dollars].[cents]
     */
    private static BigDecimal toDollars(BigDecimal amount) {
        return amount.divide(new BigDecimal("100"), ROUNDING_MODE);
    }

    /**
     * Returns true if a given string is a valid amount.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX) && isWithinRange(new BigDecimal(test));
    }

    private static boolean isWithinRange(BigDecimal value) {
        return value.compareTo(MAX_VALUE) <= 0 && value.compareTo(MIN_VALUE) >= 0;
    }

    /**
     * Returns a new {@code Amount} as the sum of the current {@code Amount} and the specified {@code Amount}.
     * Uses the minTerm constructor so no need to divide the values by 100 beforehand.
     */
    public Amount add(Amount other) {
        requireNonNull(other);
        return new Amount(this.value.add(other.value));
    }

    /**
     * Returns a new {@code Amount} as the difference of the current {@code Amount} and the specified {@code Amount}.
     * Uses the minTerm constructor so no need to divide the values by 100 beforehand.
     */
    public Amount subtract(Amount other) {
        requireNonNull(other);
        return new Amount(this.value.subtract(other.value));
    }

    /**
     * Returns the {@code Double} dollar value of the amount.
     * Warning: use only if absolutely required due to precision loss.
     */
    public Double getDollarAsDoubleValue() {
        return toDollars(value).doubleValue();
    }

    public static Amount zeroAmount() {
        return new Amount("0");
    }

    public boolean greaterThan(Amount other) {
        return this.value.compareTo(other.value) > 0;
    }

    public boolean smallerThan(Amount other) {
        return this.value.compareTo(other.value) < 0;
    }

    public boolean greaterThanEquals(Amount other) {
        return this.value.compareTo(other.value) >= 0;
    }

    public boolean smallerThanEquals(Amount other) {
        return this.value.compareTo(other.value) <= 0;
    }

    @Override
    public String toString() {
        BigDecimal output = toDollars(this.value);
        return output.setScale(2, ROUNDING_MODE).toString();
    }

    /**
     * Equality for Amount.
     * Note that BigDecimal equality should be calculated irrespective of scale.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && value.compareTo(((Amount) other).value) == 0); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Amount o) {
        return this.value.compareTo(o.value);
    }
}
