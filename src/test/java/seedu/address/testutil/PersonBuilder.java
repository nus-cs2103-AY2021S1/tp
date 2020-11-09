package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.information.Address;
import seedu.address.model.information.BlacklistStatus;
import seedu.address.model.information.Date;
import seedu.address.model.information.Email;
import seedu.address.model.information.Experience;
import seedu.address.model.information.Name;
import seedu.address.model.information.Person;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Salary;
import seedu.address.model.information.UrlLink;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_EXPERIENCE = "5.0";
    public static final String DEFAULT_DATE_OF_APPLICATION = "02-10-19";
    public static final String DEFAULT_BLACKLIST_STATUS = "false";

    private Name name;
    private Phone phone;
    private Email email;
    private Experience experience;
    private Date dateOfApplication;
    private BlacklistStatus blacklistStatus;
    private Optional<Address> addressOptional;
    private Optional<UrlLink> urlLinkOptional;
    private Optional<Salary> salaryOptional;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        experience = new Experience(DEFAULT_EXPERIENCE);
        dateOfApplication = new Date(DEFAULT_DATE_OF_APPLICATION);
        blacklistStatus = new BlacklistStatus(DEFAULT_BLACKLIST_STATUS);
        addressOptional = Optional.empty();
        urlLinkOptional = Optional.empty();
        salaryOptional = Optional.empty();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        experience = personToCopy.getExperience();
        dateOfApplication = personToCopy.getDateOfApplication();
        blacklistStatus = personToCopy.getBlacklistStatus();
        addressOptional = personToCopy.getAddressOptional();
        urlLinkOptional = personToCopy.getUrlLinkOptional();
        salaryOptional = personToCopy.getSalaryOptional();
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
     * Sets the {@code Experience} of the {@code Person} that we are building.
     */
    public PersonBuilder withExperience(String experience) {
        this.experience = new Experience(experience);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Person} that we are building.
     */
    public PersonBuilder withDateOfApplication(String dateOfApplication) {
        this.dateOfApplication = new Date(dateOfApplication);
        return this;
    }

    /**
     * Sets the {@code BlacklistStatus} of the {@code Person} that we are building.
     */
    public PersonBuilder withBlacklistStatus(String isBlacklisted) {
        this.blacklistStatus = new BlacklistStatus(isBlacklisted);
        return this;
    }

    /**
     * Sets the {@code Optional<Address>} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.addressOptional = Optional.of(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Optional<UrlLink>} of the {@code Person} that we are building.
     */
    public PersonBuilder withUrlLink(String link) {
        this.urlLinkOptional = Optional.of(new UrlLink(link));
        return this;
    }

    /**
     * Sets the {@code Optional<Salary>} of the {@code Person} that we are building.
     */
    public PersonBuilder withSalary(String salary) {
        this.salaryOptional = Optional.of(new Salary(salary));
        return this;
    }

    /**
     * Builds a Person using the attributes.
     */
    public Person build() {
        return new Person(name, phone, email, experience, dateOfApplication,
                blacklistStatus, addressOptional, urlLinkOptional, salaryOptional, tags);
    }

}
