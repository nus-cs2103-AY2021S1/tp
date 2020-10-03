package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.log.Address;
import seedu.address.model.log.Email;
import seedu.address.model.log.Log;
import seedu.address.model.log.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Name;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Log objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code logToCopy}.
     */
    public PersonBuilder(Log logToCopy) {
        name = logToCopy.getName();
        phone = logToCopy.getPhone();
        email = logToCopy.getEmail();
        address = logToCopy.getAddress();
        tags = new HashSet<>(logToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Log} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Log} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Log} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Log} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Log} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Log build() {
        return new Log(name, phone, email, address, tags);
    }

}
