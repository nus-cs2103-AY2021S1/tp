package seedu.address.testutil.bidder;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.id.BidderId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class BidderBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final BidderId DEFAULT_BIDDER_ID = new BidderId(0);
    public static final Tag DEFAULT_BIDDER_TAG = new Tag("bidder");

    private Name name;
    private Phone phone;
    private Tag tag;
    private BidderId id;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public BidderBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        tag = DEFAULT_BIDDER_TAG;
        id = DEFAULT_BIDDER_ID;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public BidderBuilder(Bidder bidderToCopy) {
        name = bidderToCopy.getName();
        phone = bidderToCopy.getPhone();
        tag = bidderToCopy.getTag();
        id = bidderToCopy.getId();
    }

    /**
     * Sets the {@code Name} of the {@code Bidder} that we are building.
     */
    public BidderBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Bidder} that we are building.
     */
    public BidderBuilder withTags(String ... tags) {
        this.tag = SampleDataUtil.getTagSet(tags);
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
        return new Bidder(name, phone, tags, id);
    }

}
