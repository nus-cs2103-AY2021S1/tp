package seedu.address.model.expense;

/**
 * Represents a Expense's exchange rate in the expense book.
 */
public class ExchangeRate {
    private final double exchangeRate;

    public ExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return String.format("%.8f", this.exchangeRate);
    }

    public double getRate() {
        return this.exchangeRate;
    }

}
