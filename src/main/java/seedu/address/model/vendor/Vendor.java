package seedu.address.model.vendor;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.menu.Menu;
import seedu.address.model.tag.Tag;

public class Vendor {
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Menu menu;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Vendor(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Menu menu) {
        requireAllNonNull(name, phone, email, address, tags, menu);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.menu = menu;
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

    public Address getAddress() {
        return address;
    }

    public Menu getMenu() {
        return menu;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both vendors of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two vendors.
     */
    public boolean isSameVendor(Vendor otherVendor) {
        if (otherVendor == this) {
            return true;
        }

        return otherVendor != null
                && otherVendor.getName().equals(getName())
                && (otherVendor.getPhone().equals(getPhone()) || otherVendor.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both vendors have the same identity and data fields.
     * This defines a stronger notion of equality between two vendors.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Vendor)) {
            return false;
        }

        Vendor otherVendor = (Vendor) other;
        return otherVendor.getName().equals(getName())
                && otherVendor.getPhone().equals(getPhone())
                && otherVendor.getEmail().equals(getEmail())
                && otherVendor.getAddress().equals(getAddress())
                && otherVendor.getTags().equals(getTags())
                && otherVendor.getMenu().equals(getMenu());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, menu);
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
        //TODO append menu?
        return builder.toString();
    }

}
