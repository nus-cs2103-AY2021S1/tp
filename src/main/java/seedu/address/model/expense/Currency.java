package seedu.address.model.expense;

/**
 * Represents a Expense's currency in the expense book.
 */
public class Currency {
    private static final String singaporeDollar = "SGD";
    private static final CurrencyConverter converter = new CurrencyConverter();

    public final String currencyCode;



    public Currency() {
        this.currencyCode = singaporeDollar;
    }

    public Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public static boolean isValidCurrency(Currency currency) {
        return converter.isValidCurrency(currency);
    }

    @Override
    public int hashCode() {
        return currencyCode.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Currency // instanceof handles nulls
            && currencyCode.equals(((Currency) other).currencyCode)); // state check
    }

    @Override
    public String toString() {
        return currencyCode;
    }

}
