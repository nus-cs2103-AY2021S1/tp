package seedu.address.model.id;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class BidderId extends Id {

    public static final String PREFIX_BIDDER_ID = "B";
    public static final String VALIDATION_REGEX = "[B]\\p{Digit}+";
    public static final String MESSAGE_CONSTRAINTS = "Bidder Id should start with a \""
            + PREFIX_BIDDER_ID
            + "\", followed by"
            + "some numbers.";
    public static final BidderId DEFAULT_BIDDER_ID = new BidderId("B0");

    public BidderId(int idNumber) {
        super(PREFIX_BIDDER_ID, idNumber);
    }

    /**
     * Constructs a BidderId object from a string.
     *
     * @param id The id in string format.
     */
    public BidderId(String id) {
        super(id);
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
    }

    @Override
    public BidderId increment() {
        return new BidderId(idNumber + 1);
    }

    /** Returns true if a given string is a valid Id. */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
