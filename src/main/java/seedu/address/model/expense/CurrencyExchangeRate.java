package seedu.address.model.expense;

/**
 * Represents a Expense's currency exchange rate in the expense book.
 */
public class CurrencyExchangeRate {

    private final String currency;
    private final String exchangeRate;

    /**
     * Constructs a CurrencyExchangeRate object.
     *
     * @param currency The name of the currency.
     * @param exchangeRate Exchange rate of this currency to SGD.
     */
    public CurrencyExchangeRate(String currency, String exchangeRate) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    /**
     * Gets the name of the currency.
     *
     * @return The name of this currency.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Gets the exchange rate of this currency to SGD.
     *
     * @return The exchange rate of this currency to SGD.
     */
    public String getExchangeRate() {
        return exchangeRate;
    }
}
