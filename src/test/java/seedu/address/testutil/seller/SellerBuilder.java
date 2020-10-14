package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.id.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class SellerBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ID_PREFIX = "S";
    public static final int DEFAULT_ID_NUMBER = 12;


    private Name name;
    private Phone phone;
    private Set<Tag> tags;
    private Id id;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public SellerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        tags = new HashSet<>();
        id = new Id(DEFAULT_ID_PREFIX, DEFAULT_ID_NUMBER);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public SellerBuilder(Seller bidderToCopy) {
        name = bidderToCopy.getName();
        phone = bidderToCopy.getPhone();
        tags = new HashSet<>(bidderToCopy.getTags());
        id = bidderToCopy.getId();
    }

    /**
     * Sets the {@code Name} of the {@code Seller} that we are building.
     */
    public SellerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Seller} that we are building.
     */
    public SellerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Seller} that we are building.
     */
    public SellerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Seller} that we are building.
     */
    public SellerBuilder withId(String prefix, int number) {
        this.id = new Id(prefix, number);
        return this;
    }

    public Seller build() {
        return new Seller(name, phone, tags, id);
    }

}
