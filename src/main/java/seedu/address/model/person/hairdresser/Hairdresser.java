package seedu.address.model.person.hairdresser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.IdCounter;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.deletedfields.DeletedEmail;
import seedu.address.model.person.deletedfields.DeletedName;
import seedu.address.model.person.deletedfields.DeletedPhone;
import seedu.address.model.specialisation.Specialisation;

/**
 * Represents a Hairdresser in HairStyleX.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Hairdresser extends Person {

    private final HairdresserId id;
    // Data fields
    private final Title title;
    private final Set<Specialisation> specList = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Hairdresser(Name name, Phone phone, Email email, Gender gender, Title title, Set<Specialisation> specList) {
        super(name, phone, email, gender);
        requireAllNonNull(title, specList);
        this.title = title;
        this.specList.addAll(specList);
        this.id = IdCounter.getInstance().generateNewHairdresserId();
    }

    /**
     * This is an existing hairdresser and does not need to generate a new ID.
     */
    public Hairdresser(HairdresserId id, Name name, Phone phone, Email email, Gender gender, Title title,
                       Set<Specialisation> specList) {
        super(name, phone, email, gender);
        requireAllNonNull(title, specList);
        this.title = title;
        this.specList.addAll(specList);
        this.id = id;
    }

    public Title getTitle() {
        return this.title;
    }

    /**
     * Returns an immutable specialisation set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Specialisation> getSpecs() {
        return Collections.unmodifiableSet(specList);
    }

    @Override
    public HairdresserId getId() {
        return this.id;
    }

    /**
     * Returns a hairdresser which has been previously deleted
     */
    public Hairdresser setTombstone() {
        return new Hairdresser(this.getId(), DeletedName.getInstance(), DeletedPhone.getInstance(),
                DeletedEmail.getInstance(), this.getGender(), this.getTitle(), this.getSpecs());
    }

    /**
     * Returns true if both hairdressers have the same identity and data fields.
     * This defines a stronger notion of equality between two hairdressers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Hairdresser)) {
            return false;
        }

        Hairdresser otherHairdresser = (Hairdresser) other;
        return otherHairdresser.getName().equals(getName())
                && otherHairdresser.getPhone().equals(getPhone())
                && otherHairdresser.getEmail().equals(getEmail())
                && otherHairdresser.getTitle().equals(getTitle())
                && otherHairdresser.getGender().equals(getGender())
                && otherHairdresser.getSpecs().equals(getSpecs());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, specList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString())
                .append(" Title: ")
                .append(getTitle())
                .append(" Specialisations: ");
        getSpecs().forEach(builder::append);
        return builder.toString();
    }
}
