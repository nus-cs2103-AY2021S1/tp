package seedu.address.model.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

//@@author chiamyunqing
/**
 * Represents a Patient in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient {

    private final Name name; //identifier field
    private final Temperature temperature;
    private final PeriodOfStay periodOfStay;
    private final Phone phone;
    private final Age age;
    private final Comment comment; // an optional field, if null is initialised to "-"

    /**
     * Every field, except comment, must be present and not null.
     */
    public Patient(Name name, Temperature temperature, PeriodOfStay periodOfStay,
                   Phone phone, Age age, Comment comment) {
        requireAllNonNull(name, temperature, periodOfStay, phone, age);
        this.name = name;
        this.temperature = temperature;
        this.periodOfStay = periodOfStay;
        this.phone = phone;
        this.age = age;
        this.comment = comment == null ? new Comment("-") : comment;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public PeriodOfStay getPeriodOfStay() {
        return periodOfStay;
    }

    public Age getAge() {
        return age;
    }

    public Comment getComment() {
        return comment;
    }

    /**
     * Returns true if both patients have the same name.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }
        return otherPatient != null
                && otherPatient.getName().equals(getName());
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return otherPatient.getName().equals(getName())
                && otherPatient.getTemperature().equals(getTemperature())
                && otherPatient.getPeriodOfStay().equals(getPeriodOfStay())
                && otherPatient.getPhone().equals(getPhone())
                && otherPatient.getAge().equals(getAge());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, temperature, periodOfStay, phone, age, comment);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName() + "\n")
                .append("Temperature: ")
                .append(getTemperature() + "\n")
                .append("Period of stay: ")
                .append(getPeriodOfStay().readerFriendlyString() + "\n")
                .append("Phone: ")
                .append(getPhone() + "\n")
                .append("Age: ")
                .append(getAge() + "\n")
                .append("Comment: ")
                .append(getComment() + "\n");
        return builder.toString();
    }
}
