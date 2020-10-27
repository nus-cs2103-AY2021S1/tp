package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.price.Price;
import seedu.address.model.price.PriceFilter;
import seedu.address.testutil.property.PropertyBuilder;

class AskingPricePredicateTest {

    @Test
    void test_passesFilter_returnsTrue() {
        PriceFilterStub priceFilter = new PriceFilterStub();
        AskingPricePredicate askingPricePredicate = new AskingPricePredicate(priceFilter);
        Property property = new PropertyBuilder().withAskingPrice(100).build();
        assertTrue(askingPricePredicate.test(property));
        property = new PropertyBuilder().withAskingPrice(101).build();
        assertTrue(askingPricePredicate.test(property));
    }

    @Test
    void test_failsFilter_returnsFalse() {
        PriceFilterStub priceFilter = new PriceFilterStub();
        AskingPricePredicate askingPricePredicate = new AskingPricePredicate(priceFilter);
        Property property = new PropertyBuilder().withAskingPrice(99).build();
        assertFalse(askingPricePredicate.test(property));
    }

    /**
     * A stub to represent PriceFilter for greater than or equals to comparisons.
     */
    private class PriceFilterStub extends PriceFilter {

        private final Price target = new Price(100);

        /**
         * Constructs a {@code PriceFilter} object from a String.
         */
        public PriceFilterStub() {
            super(">= 100");
        }

        @Override
        public boolean test(Price price) {
            return price.compareTo(target) >= 0;
        }
    }
}
