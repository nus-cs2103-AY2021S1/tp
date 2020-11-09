package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.price.Price;
import seedu.address.model.price.PriceFilter;
import seedu.address.testutil.property.PropertyBuilder;

class AskingPricePredicateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AskingPricePredicate(null));
    }

    @Test
    public void test_passesFilter_returnsTrue() {
        PriceFilterStub priceFilter = new PriceFilterStub();
        AskingPricePredicate askingPricePredicate = new AskingPricePredicate(priceFilter);
        Property property = new PropertyBuilder().withAskingPrice(100).build();
        assertTrue(askingPricePredicate.test(property));
        property = new PropertyBuilder().withAskingPrice(101).build();
        assertTrue(askingPricePredicate.test(property));
    }

    @Test
    public void test_failsFilter_returnsFalse() {
        PriceFilterStub priceFilter = new PriceFilterStub();
        AskingPricePredicate askingPricePredicate = new AskingPricePredicate(priceFilter);
        Property property = new PropertyBuilder().withAskingPrice(99).build();
        assertFalse(askingPricePredicate.test(property));
    }

    @Test
    public void equals() {
        AskingPricePredicate askingPricePredicate =
                new AskingPricePredicate(new PriceFilter("< 100"));

        // same object
        assertTrue(askingPricePredicate.equals(askingPricePredicate));

        // different type
        assertFalse(askingPricePredicate.equals(new PriceFilter("< 100")));

        // same asking price predicate
        assertTrue(askingPricePredicate.equals(
                new AskingPricePredicate(new PriceFilter("< 100"))));

        // different price filter
        assertFalse(askingPricePredicate.equals(
                new AskingPricePredicate(new PriceFilter("> 100"))));
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
