package seedu.address.model.information;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    public static final String TAB_NAME = "Candidates";

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Experience experience;
    private final Date dateOfApplication;
    private final BlacklistStatus blacklistStatus;
    private final Optional<Address> addressOptional;
    private final Optional<UrlLink> urlLinkOptional;
    private final Optional<Salary> salaryOptional;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Experience experience, Date dateOfApplication,
                  BlacklistStatus blacklistStatus, Optional<Address> addressOptional,
                  Optional<UrlLink> urlLinkOptional, Optional<Salary> salaryOptional, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, addressOptional, experience, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.experience = experience;
        this.dateOfApplication = dateOfApplication;
        this.blacklistStatus = blacklistStatus;
        this.addressOptional = addressOptional;
        this.urlLinkOptional = urlLinkOptional;
        this.salaryOptional = salaryOptional;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Experience getExperience() {
        return experience;
    }

    public Date getDateOfApplication() {
        return dateOfApplication;
    }

    public BlacklistStatus getBlacklistStatus() {
        return blacklistStatus;
    }

    public Optional<Address> getAddressOptional() {
        return addressOptional;
    }

    public Optional<UrlLink> getUrlLinkOptional() {
        return urlLinkOptional;
    }

    public Optional<Salary> getSalaryOptional() {
        return salaryOptional;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (otherPerson.getPhone().equals(getPhone()) || otherPerson.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getExperience().equals(getExperience())
                && otherPerson.getDateOfApplication().equals(getDateOfApplication())
                && otherPerson.getBlacklistStatus().equals(getBlacklistStatus())
                && otherPerson.getAddressOptional().equals(getAddressOptional())
                && otherPerson.getUrlLinkOptional().equals(getUrlLinkOptional())
                && otherPerson.getSalaryOptional().equals(getSalaryOptional())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, experience, dateOfApplication, blacklistStatus,
                addressOptional, urlLinkOptional, salaryOptional, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Experience: ")
                .append(getExperience().toString() + " years")
                .append(" Date of Application: ")
                .append(getDateOfApplication());
        builder.append(" Address: ");
        getAddressOptional().ifPresent(address -> builder.append(address.value));
        builder.append(" Link: ");
        getUrlLinkOptional().ifPresent(link -> builder.append(link.value));
        builder.append(" Expected Salary: ");
        getSalaryOptional().ifPresent(salary -> builder.append("$" + salary.toString()));
        builder.append(" Blacklisted: ").append(getBlacklistStatus());
        builder.append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
