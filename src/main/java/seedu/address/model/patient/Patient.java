package seedu.address.model.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.allergy.Allergy;

/**
 * Represents a Patient in Hospify.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Nric nric;

    // Data fields
    private final Address address;
    private final Set<Allergy> allergies = new HashSet<>();
    private final Set<Appointment> appointments = new HashSet<>();
    private final MedicalRecord medicalRecord;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Nric nric, Phone phone, Email email, Address address,
                   Set<Allergy> allergies, Set<Appointment> appointments, MedicalRecord medicalRecord) {
        requireAllNonNull(name, phone, nric, email, address, allergies, appointments);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.nric = nric;
        this.allergies.addAll(allergies);
        this.appointments.addAll(appointments);
        this.medicalRecord = medicalRecord;
    }

    public Name getName() {
        assert name != null : "Name should not be empty";
        return name;
    }

    public Phone getPhone() {
        assert phone != null : "Phone number should not be empty";
        return phone;
    }

    public Email getEmail() {
        assert email != null : "Email should not be empty";
        return email;
    }

    public Address getAddress() {
        assert address != null : "Address should not be empty";
        return address;
    }

    public Nric getNric() {
        assert nric != null : "Nric should not be empty";
        return nric;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    /**
     * Returns an immutable allergy set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Allergy> getAllergies() {
        return Collections.unmodifiableSet(allergies);
    }

    /**
     * Returns an immutable appointment set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Appointment> getAppointments() {
        return Collections.unmodifiableSet(appointments);
    }

    public Set<Appointment> getModifiableAppointments() {
        return appointments;
    }

    /**
     * Returns true if both patients of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getName().equals(getName())
                && otherPatient.getNric().equals(getNric())
                && otherPatient.getMedicalRecord().equals(getMedicalRecord())
                && (otherPatient.getPhone().equals(getPhone()) || otherPatient.getEmail().equals(getEmail()));
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
                && otherPatient.getPhone().equals(getPhone())
                && otherPatient.getEmail().equals(getEmail())
                && otherPatient.getAddress().equals(getAddress())
                && otherPatient.getNric().equals(getNric())
                && otherPatient.getAllergies().equals(getAllergies())
                && otherPatient.getAppointments().equals(getAppointments())
                && otherPatient.getMedicalRecord().equals(getMedicalRecord());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, nric, phone, email, address, allergies, appointments, medicalRecord);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" NRIC: ")
                .append(getNric())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Address: ")
                .append(getAddress())
                .append(" Email: ")
                .append(getEmail())
                .append(" Allergies: ");
        getAllergies().forEach(builder::append);
        builder.append(" Appointments: ");
        getAppointments().forEach(builder::append);
        builder.append(" Medical Record URL: ")
                .append(getMedicalRecord());
        return builder.toString();
    }
}
