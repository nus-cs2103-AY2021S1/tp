package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUserName;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building Project objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_PERSON_NAME = "Alice Pauline";
    public static final String DEFAULT_GIT_USERNAME = "Alice32";
    public static final String DEFAULT_PHONE = "88888888";
    public static final String DEFAULT_EMAIL = "alicepauline@sample.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private PersonName personName;
    private GitUserName gitUserName;
    private Phone phone;
    private Email email;
    private Address address;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        personName = new PersonName(DEFAULT_PERSON_NAME);
        gitUserName = new GitUserName(DEFAULT_GIT_USERNAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        personName = personToCopy.getPersonName();
        gitUserName = personToCopy.getGitUserName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
    }

    /**
     * Sets the {@code PersonName} of the {@code Person} that we are building.
     */
    public PersonBuilder withPersonName(String personName) {
        this.personName = new PersonName(personName);
        return this;
    }

    /**
     * Sets the {@code GitUserName} of the {@code Person} that we are building.
     */
    public PersonBuilder withGitUserName(String gitUserName) {
        this.gitUserName = new GitUserName(gitUserName);
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
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    public Person build() {
        return new Person(personName, gitUserName, phone, email, address);
    }

}
