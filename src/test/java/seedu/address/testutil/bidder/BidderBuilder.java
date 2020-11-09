package seedu.address.testutil.bidder;

import seedu.address.model.id.BidderId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.bidder.Bidder;

/**
 * A utility class to help with building Person objects.
 */
public class BidderBuilder {

    public static final String DEFAULT_NAME = "Kor Ming Soon";
    public static final String DEFAULT_PHONE = "85355255";
    public static final BidderId DEFAULT_BIDDER_ID = new BidderId(0);

    private Name name;
    private Phone phone;
    private BidderId id;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public BidderBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        id = DEFAULT_BIDDER_ID;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public BidderBuilder(Bidder bidderToCopy) {
        name = bidderToCopy.getName();
        phone = bidderToCopy.getPhone();
        id = (BidderId) bidderToCopy.getId();
    }

    /**
     * Sets the {@code Name} of the {@code Bidder} that we are building.
     */
    public BidderBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Bidder} that we are building.
     */
    public BidderBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Bidder} that we are building.
     */
    public BidderBuilder withId(int number) {
        this.id = new BidderId(number);
        return this;
    }

    public Bidder build() {
        return new Bidder(name, phone, id);
    }

}
