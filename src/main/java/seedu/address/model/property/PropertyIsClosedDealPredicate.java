package seedu.address.model.property;

import java.util.function.Predicate;

/**
 * Tests if the {@code IsClosedDeal} matches the target {@code IsClosedDeal}.
 */
public class PropertyIsClosedDealPredicate implements Predicate<Property> {

    private final IsClosedDeal target;

    public PropertyIsClosedDealPredicate(IsClosedDeal target) {
        this.target = target;
    }

    @Override
    public boolean test(Property property) {
        return property.isClosedDeal().equals(target);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof PropertyIsClosedDealPredicate
        && this.target.equals(((PropertyIsClosedDealPredicate) obj).target));
    }
}
