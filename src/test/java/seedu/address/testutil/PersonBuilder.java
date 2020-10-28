package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.clientsource.ClientSource;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyName;
import seedu.address.model.util.SampleClientDataUtil;

import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_DESCRIPTION;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_NAME;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_NOTE = "sd";
    public static final boolean DEFAULT_IS_ARCHIVE = false;
    public static final String DEFAULT_PRIORITY = "u";
    public static final String DEFAULT_POLICY_NAME = LIFE_TIME_NAME; //PolicyName from typicalpolicies
    public static final String DEFAULT_POLICY_DESCRIPTION = LIFE_TIME_DESCRIPTION; //PolicyDescription from typicalpolicies

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<ClientSource> clientSources;
    private Note note;
    private boolean isArchive;
    private Priority priority;
    private Policy policy;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        clientSources = new HashSet<>();
        note = new Note(DEFAULT_NOTE);
        isArchive = DEFAULT_IS_ARCHIVE;
        priority = new Priority(DEFAULT_PRIORITY);
        policy = new Policy(new PolicyName(DEFAULT_POLICY_NAME), new PolicyDescription(DEFAULT_POLICY_DESCRIPTION));
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        clientSources = new HashSet<>(personToCopy.getClientSources());
        note = personToCopy.getNote();
        isArchive = personToCopy.getIsArchive();
        priority = personToCopy.getPriority();
        policy = personToCopy.getPolicy();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code clientSources} into a {@code Set<ClientSource>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withClientSources(String ... clientSources) {
        this.clientSources = SampleClientDataUtil.getClientSourceSet(clientSources);
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
     * Sets the {@code Address} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withoutAddress() {
        this.address = null;
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
     * Sets the {@code Phone} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withoutPhone() {
        this.phone = null;
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withoutEmail() {
        this.email = null;
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Person} that we are building.
     */
    public PersonBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withoutNote() {
        this.note = null;
        return this;
    }

    /**
     * Sets the {@code isArchive} of the {@code Person} that we are building to true.
     */
    public PersonBuilder addToArchive() {
        this.isArchive = true;
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Person} that we are building.
     */
    public PersonBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withoutPriority() {
        this.priority = new Priority(null);
        return this;
    }

    /**
     * Sets the {@code Policy} of the {@code Person} that we are building.
     */
    public PersonBuilder withPolicy(String name, String description) {
        policy = new Policy(new PolicyName(name), new PolicyDescription(description));
        return this;
    }

    /**
     * Sets the {@code Policy} of the {@code Person} that we are building.
     */
    public PersonBuilder withoutPolicy() {
        policy = null;
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, clientSources, note, isArchive, priority, policy);
    }

}
