package seedu.address.testutil.builders;

import seedu.address.model.commons.Name;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.Mobile;
import seedu.address.model.friend.Passport;

/**
 * A utility class to help with building Friend objects.
 */
public class FriendBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PASSPORT = "E1233567A";
    public static final String DEFAULT_MOBILE = "85355255";

    private Name name;
    private Passport passport;
    private Mobile mobile;

    /**
     * Creates a {@code FriendBuilder} with the default details.
     */
    public FriendBuilder() {
        name = new Name(DEFAULT_NAME);
        passport = new Passport(DEFAULT_PASSPORT);
        mobile = new Mobile(DEFAULT_MOBILE);
    }

    /**
     * Initializes the FriendBuilder with the data of {@code friendToCopy}.
     */
    public FriendBuilder(Friend friendToCopy) {
        name = friendToCopy.getName();
        passport = friendToCopy.getPassport();
        mobile = friendToCopy.getMobile();
    }

    /**
     * Sets the {@code Name} of the {@code Friend} that we are building.
     */
    public FriendBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Mobile} of the {@code Friend} that we are building.
     */
    public FriendBuilder withMobile(String phone) {
        this.mobile = new Mobile(phone);
        return this;
    }

    /**
     * Sets the {@code Passport} of the {@code Friend} that we are building.
     */
    public FriendBuilder withPassport(String passport) {
        this.passport = new Passport(passport);
        return this;
    }

    public Friend build() {
        return new Friend(name, passport, mobile);
    }
}
