package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CurrencyTest {

    @Test
    public void isValidCurrency() {
        // invalid Currency
        assertFalse(Currency.isValidCurrency(new Currency("oop"))); //invalid currency code
        assertFalse(Currency.isValidCurrency(new Currency("1"))); //invalid currency code

        // valid Currency
        assertTrue(Currency.isValidCurrency(new Currency())); // Default SGD
        assertTrue(Currency.isValidCurrency(new Currency("CNY"))); // Chinese Yuan
    }
}
