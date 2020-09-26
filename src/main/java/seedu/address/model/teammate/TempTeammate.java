package seedu.address.model.teammate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Teammate in the team.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TempTeammate {

    // Identity fields
    private TempName name;
    private TempPhone phone;
    private TempEmail email;

    // Data fields
    private TempAddress address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public TempTeammate(TempName name, TempPhone phone, TempEmail email, TempAddress address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    public TempName getName() {
        return name;
    }

    public TempPhone getPhone() {
        return phone;
    }

    public TempEmail getEmail() {
        return email;
    }

    public TempAddress getAddress() {
        return address;
    }

    public void updateName(String newNameStr) {
        name = new TempName(newNameStr);
    }

    public void updateAddress(String newAddressStr) {
        address = new TempAddress(newAddressStr);
    }

    public void updatePhone(String newPhonestr) {
        phone = new TempPhone(newPhonestr);
    }

    public void updateEmail(String newEmailStr) {
        email = new TempEmail(newEmailStr);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both teammates of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameTeammate(TempTeammate otherTeammate) {
        if (otherTeammate == this) {
            return true;
        }

        return otherTeammate != null
                && otherTeammate.getName().equals(getName())
                && (otherTeammate.getPhone().equals(getPhone())
                        || otherTeammate.getEmail().equals(getEmail())
                        || otherTeammate.getAddress().equals(getAddress()));
    }

    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TempTeammate)) {
            return false;
        }

        TempTeammate otherProject = (TempTeammate) other;
        return otherProject.getName().equals(getName())
                && otherProject.getPhone().equals(getPhone())
                && otherProject.getEmail().equals(getEmail())
                && otherProject.getAddress().equals(getAddress())
                && otherProject.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
