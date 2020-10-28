package seedu.pivot.model.investigationcase.caseperson;

import static seedu.pivot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Encapsulates a Person related to an Investigation Case.
 */
public abstract class CasePerson {
    // Identity fields
    private final Name name;
    private final Gender gender;

    // Data fields
    private final Phone phone;
    private final Email email;
    private final Address address;

    /**
     * Every field must be present and not null.
     */
    public CasePerson(Name name, Gender gender, Phone phone, Email email, Address address) {
        requireAllNonNull(name, gender, phone, email, address);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Name getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public abstract boolean equals(Object other);

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, gender, phone, email, address);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Gender: ")
                .append(getGender());

        if (!phone.toString().isBlank()) {
            builder.append(" Phone: ")
                    .append(getPhone());
        }

        if (!email.toString().isBlank()) {
            builder.append(" Email: ")
                    .append(getEmail());
        }

        if (!address.toString().isBlank()) {
            builder.append(" Address: ")
                    .append(getAddress());
        }

        return builder.toString();
    }


}
