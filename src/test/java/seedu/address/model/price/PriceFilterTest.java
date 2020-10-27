package seedu.address.model.price;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PriceFilterTest {

    @Test
    void isValidPriceFilter() {

        // invalid
        assertFalse(PriceFilter.isValidPriceFilter(""));
        assertFalse(PriceFilter.isValidPriceFilter(">> 2000"));
        assertFalse(PriceFilter.isValidPriceFilter("random price"));
        assertFalse(PriceFilter.isValidPriceFilter("500"));
        assertFalse(PriceFilter.isValidPriceFilter(">="));

        // valid
        assertTrue(PriceFilter.isValidPriceFilter("<= 100"));
        assertTrue(PriceFilter.isValidPriceFilter("< 100"));
        assertTrue(PriceFilter.isValidPriceFilter("== 100"));
        assertTrue(PriceFilter.isValidPriceFilter("> 100"));
        assertTrue(PriceFilter.isValidPriceFilter(">= 100"));
        assertTrue(PriceFilter.isValidPriceFilter("      <=     100    "));
    }

    @Test
    void test() {
        Price hundred = new Price(100);
        Price ninetyNine = new Price(99);
        Price hundredAndOne = new Price(101);

        // <= filter
        PriceFilter priceFilter = new PriceFilter("<= 100");
        assertTrue(priceFilter.test(ninetyNine));
        assertTrue(priceFilter.test(hundred));
        assertFalse(priceFilter.test(hundredAndOne));

        // < filter
        priceFilter = new PriceFilter("< 100");
        assertTrue(priceFilter.test(ninetyNine));
        assertFalse(priceFilter.test(hundred));
        assertFalse(priceFilter.test(hundredAndOne));

        // == filter
        priceFilter = new PriceFilter("== 100");
        assertFalse(priceFilter.test(ninetyNine));
        assertTrue(priceFilter.test(hundred));
        assertFalse(priceFilter.test(hundredAndOne));

        // >= filter
        priceFilter = new PriceFilter(">= 100");
        assertFalse(priceFilter.test(ninetyNine));
        assertTrue(priceFilter.test(hundred));
        assertTrue(priceFilter.test(hundredAndOne));

        // > filter
        priceFilter = new PriceFilter("> 100");
        assertFalse(priceFilter.test(ninetyNine));
        assertFalse(priceFilter.test(hundred));
        assertTrue(priceFilter.test(hundredAndOne));
    }
}
