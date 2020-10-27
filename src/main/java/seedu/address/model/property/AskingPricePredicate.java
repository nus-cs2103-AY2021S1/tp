package seedu.address.model.property;

import java.util.function.Predicate;

import seedu.address.model.price.PriceFilter;

/**
 * Tests that a {@code AskingPrice} matches the {@code PriceFilter} given.
 */
public class AskingPricePredicate implements Predicate<Property> {

    private final PriceFilter priceFilter;

    public AskingPricePredicate(PriceFilter priceFilter) {
        this.priceFilter = priceFilter;
    }

    @Override
    public boolean test(Property property) {
        return priceFilter.test(property.getAskingPrice());
    }
}
