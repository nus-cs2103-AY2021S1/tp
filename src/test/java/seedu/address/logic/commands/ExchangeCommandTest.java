package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalCurrencyCode;

public class ExchangeCommandTest {

    @Test
    public void equals() {
        ExchangeCommand toUsd = new ExchangeCommand(TypicalCurrencyCode.US_DOLLAR);
        ExchangeCommand toCny = new ExchangeCommand(TypicalCurrencyCode.CHINESE_YUAN);

        // same object -> returns true
        assertTrue(toUsd.equals(toUsd));

        // same values -> returns true
        ExchangeCommand toUsdCopy = new ExchangeCommand(TypicalCurrencyCode.US_DOLLAR);
        assertTrue(toUsd.equals(toUsdCopy));

        // different types -> returns false
        assertFalse(toCny.equals(1));

        // null -> returns false
        assertFalse(toCny.equals(null));

        // different currencies -> returns false
        assertFalse(toUsd.equals(toCny));
    }


}
