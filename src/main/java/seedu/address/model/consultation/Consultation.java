package seedu.address.model.consultation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Name;

/**
 * Represents a Consultation in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Consultation {
    // Identity fields
    private final Name name;
    private final Day date;
    private final Time time;
    private final Address location;
    private final Type type;

    /**
     * Class constructor for a consultation object.
     * Every field must be present and not null.
     *
     * @param name name of person consulting.
     * @param date date of consultation.
     * @param time time of consultation.
     * @param location location of consultation.
     * @param type type of consultation.
     */
    public Consultation(Name name, Day date, Time time, Address location, Type type) {
        requireAllNonNull(name, date, time, location, type);
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.type = type;
    }
    public Name getName() {
        return name;
    }
    public Day getDate() {
        return date;
    }

    public Address getLocation() {
        return location;
    }

    public Time getTime() {
        return time;
    }

    public Type getType() {
        return type;
    }
    /**
     * Returns true if both consultation of the same date, time have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two consultations.
     *
     * @param consultation Consultation object being checked.
     * @return Presence of same consultation.
     */
    public boolean isSameConsultation(Consultation consultation) {
        if (consultation == this) {
            return true;
        }

        return consultation != null
                && consultation.getDate().equals(getDate())
                && consultation.getTime().equals(getTime())
                && consultation.getName().equals(getName());
    }

    /**
     * Returns true if a personal consultation is added on the same time & day as another consultation.
     *
     * @param consultation Consultation to be added.
     * @return Presence of conflicting consultation.
     */
    public boolean isPersonalConsultationOnSameTiming(Consultation consultation) {
        if (consultation == this) {
            return true;
        }
        // transitive: adding personal consult to an existing group/personal consult OR
        // adding group consult to an existing personal consult
        return consultation != null
                && (consultation.getType().type.equals(ConsultationType.PERSONAL)
                || getType().type.equals(ConsultationType.PERSONAL))
                && consultation.getDate().equals(getDate())
                && consultation.getTime().equals(getTime());
    }

    /**
     * Returns true if a Group consultation is added on the same time & day as another consultation.
     *
     * @param consultation Consultation to be added.
     * @return Presence of conflicting consultation.
     */
    public boolean isGroupConsultationOnSameTimingAndDifferentLocation(Consultation consultation) {
        if (consultation == this) {
            return true;
        }

        // Group consultations can have same timing, but they must have the same location.
        return consultation != null
                && (consultation.getType().type.equals(ConsultationType.GROUP)
                || getType().type.equals(ConsultationType.GROUP))
                && consultation.getDate().equals(getDate())
                && consultation.getTime().equals(getTime())
                && !consultation.getLocation().equals(getLocation());
    }

    /**
     * Returns true if both consultation have the same data fields.
     * This defines a stronger notion of equality between two persons.
     *
     * @param other object for comparison.
     * @return Presence of whether the consultations have same fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Consultation)) {
            return false;
        }

        Consultation consultation = (Consultation) other;
        return consultation.getName().equals(getName())
                && consultation.getType().equals(getType())
                && consultation.getTime().equals(getTime())
                && consultation.getDate().equals(getDate())
                && consultation.getLocation().equals(getLocation());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, location, time, type);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Date: ")
                .append(getDate())
                .append(" Time: ")
                .append(getTime())
                .append(" Location: ")
                .append(getLocation())
                .append(" Type: ")
                .append(getType());
        return builder.toString();
    }
}

