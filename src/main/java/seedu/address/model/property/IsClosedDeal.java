package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a property's isClosedDeal in the property book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIsClosedDeal(String)}
 */
public class IsClosedDeal {

    public static final String MESSAGE_CONSTRAINTS = "IsClosedDeal can only be close or active";

    public static final String CLOSE = "close";
    public static final String ACTIVE = "active";

    public final boolean isClosedDeal;

    /**
     * Constructs an {@code IsClosedDeal}.
     *
     * @param isClosedDeal A valid isClosedDeal.
     */
    public IsClosedDeal(String isClosedDeal) {
        requireNonNull(isClosedDeal);
        checkArgument(isValidIsClosedDeal(isClosedDeal), MESSAGE_CONSTRAINTS);
        this.isClosedDeal = isClosedDeal.toLowerCase().equals(CLOSE);
    }

    /**
     * Returns true if a given string is a valid isClosedDeal.
     */
    public static boolean isValidIsClosedDeal(String test) {
        return test.toLowerCase().equals(CLOSE) || test.toLowerCase().equals(ACTIVE);
    }

    @Override
    public String toString() {
        return isClosedDeal ? "Close" : "Active";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsClosedDeal // instanceof handles nulls
                && isClosedDeal == ((IsClosedDeal) other).isClosedDeal); // state check
    }

}
