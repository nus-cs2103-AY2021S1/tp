package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person.
 */
public abstract class Person {

    // Identity fields
    protected final Name name;
    protected final Phone phone;

    // Data fields
    protected final Tag tag;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Tag tag) {
        requireAllNonNull(name, phone, tag);
        this.name = name;
        this.phone = phone;
        this.tag = tag;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns an immutable tag, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Tag getTag() {
        return this.tag;
    }



}
