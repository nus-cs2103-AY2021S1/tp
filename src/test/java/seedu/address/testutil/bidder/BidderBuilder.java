package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.id.Id;
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
    public static final String DEFAULT_ID_PREFIX = "B";
    public static final int DEFAULT_ID_NUMBER = 12;


    private Name name;
    private Phone phone;
    private Set<Tag> tags;
    private Id id;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public BidderBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        tags = new HashSet<>();
        id = new Id(DEFAULT_ID_PREFIX, DEFAULT_ID_NUMBER);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public BidderBuilder(Bidder bidderToCopy) {
        name = bidderToCopy.getName();
        phone = bidderToCopy.getPhone();
        tags = new HashSet<>(bidderToCopy.getTags());
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
        this.tags = SampleDataUtil.getTagSet(tags);
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
    public BidderBuilder withId(String prefix, int number) {
        this.id = new Id(prefix, number);
        return this;
    }

    public Bidder build() {
        return new Bidder(name, phone, tags, id);
    }

}
