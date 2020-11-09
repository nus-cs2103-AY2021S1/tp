package seedu.address.model.property;

import java.util.function.Predicate;

/**
 * Tests if the {@code IsRental} matches the target {@code IsRental}.
 */
public class PropertyIsRentalPredicate implements Predicate<Property> {

    private final IsRental target;

    public PropertyIsRentalPredicate(IsRental target) {
        this.target = target;
    }

    @Override
    public boolean test(Property property) {
        return property.getIsRental().equals(target);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof PropertyIsRentalPredicate
            && target.equals(((PropertyIsRentalPredicate) obj).target));
    }
}
