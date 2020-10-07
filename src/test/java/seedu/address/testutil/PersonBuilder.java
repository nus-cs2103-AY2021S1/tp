package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.FileAddress;
import seedu.address.model.person.Email;
import seedu.address.model.person.TagName;
import seedu.address.model.person.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private TagName name;
    private Email email;
    private FileAddress fileAddress;
    private Set<seedu.address.model.tag.Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new TagName(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        fileAddress = new FileAddress(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Tag personToCopy) {
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        fileAddress = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new TagName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code FileAddress} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.fileAddress = new FileAddress(address);
        return this;
    }


    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Tag build() {
        return new Tag(name, email, fileAddress, tags);
    }

}
