package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.price.PriceFilter;

/**
 * Tests that a {@code AskingPrice} matches the {@code PriceFilter} given.
 */
public class AskingPricePredicate implements Predicate<Property> {

    private final PriceFilter priceFilter;

    public AskingPricePredicate(PriceFilter priceFilter) {
        requireNonNull(priceFilter);
        this.priceFilter = priceFilter;
    }

    @Override
    public boolean test(Property property) {
        return priceFilter.test(property.getAskingPrice());
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof AskingPricePredicate
            && priceFilter.equals(((AskingPricePredicate) obj).priceFilter));
    }
}
