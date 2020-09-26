package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.*;
import seedu.address.model.person.Bug;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Bug objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private State state;
    private Description description;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        state = new State(DEFAULT_EMAIL);
        description = new Description(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code bugToCopy}.
     */
    public PersonBuilder(Bug bugToCopy) {
        name = bugToCopy.getName();
        phone = bugToCopy.getPhone();
        state = bugToCopy.getState();
        description = bugToCopy.getDescription();
        tags = new HashSet<>(bugToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Bug} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Bug} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Bug} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.description = new Description(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Bug} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code State} of the {@code Bug} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.state = new State(email);
        return this;
    }

    public Bug build() {
        return new Bug(name, phone, state, description, tags);
    }

}
