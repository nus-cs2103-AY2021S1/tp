package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.ArchiveStatus;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMERGENCY = "97851877";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final boolean DEFAULT_ARCHIVESTATUS = false;

    private Name name;
    private Phone phone;
    private Phone emergency;
    private Address address;
    private ArchiveStatus archiveStatus;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        emergency = new Phone(DEFAULT_EMERGENCY);
        address = new Address(DEFAULT_ADDRESS);
        archiveStatus = new ArchiveStatus(DEFAULT_ARCHIVESTATUS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        emergency = personToCopy.getEmergency();
        address = personToCopy.getAddress();
        archiveStatus = personToCopy.getArchiveStatus();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Emergency} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmergency(String emergency) {
        this.emergency = new Phone(emergency);
        return this;
    }

    /**
     * Sets the {@code Archive} of the {@code Person} that we are building.
     */
    public PersonBuilder withArchiveStatus(String state) {
        this.archiveStatus = new ArchiveStatus(Boolean.valueOf(state));
        return this;
    }

    public Person build() {
        return new Person(name, phone, emergency, address, archiveStatus, tags);
    }

}
