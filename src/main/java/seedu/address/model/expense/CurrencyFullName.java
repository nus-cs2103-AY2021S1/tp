package seedu.address.model.expense;

public class CurrencyFullName {

    private final String code;
    private final String name;

    /**
     * Constructs a CurrencyExchangeRate object.
     *
     * @param currencyCode The code of the currency.
     * @param currencyName The full name of the currency.
     */
    public CurrencyFullName(String currencyCode, String currencyName) {
        this.code = currencyCode;
        this.name = currencyName;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
