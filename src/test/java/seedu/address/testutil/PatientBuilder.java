package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_NRIC = "S0000001A";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_URL = "www.sampleurl.com/01";

    private Name name;
    private Nric nric;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Allergy> allergies;
    private Set<Appointment> appointments;
    private MedicalRecord medicalRecord;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        nric = new Nric(DEFAULT_NRIC);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        allergies = new HashSet<>();
        appointments = new HashSet<>();
        medicalRecord = new MedicalRecord(DEFAULT_URL);
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        nric = patientToCopy.getNric();
        phone = patientToCopy.getPhone();
        email = patientToCopy.getEmail();
        address = patientToCopy.getAddress();
        allergies = new HashSet<>(patientToCopy.getAllergies());
        appointments = new HashSet<>(patientToCopy.getAppointments());
        medicalRecord = patientToCopy.getMedicalRecord();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Patient} that we are building.
     */
    public PatientBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<Allergy>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withAllergies(String ... allergies) {
        this.allergies = SampleDataUtil.getAllergySet(allergies);
        return this;
    }

    /**
     * Parses the {@code appointments} into a {@code Set<Appointment>}
     * and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withAppointments(String ... appointments) {
        this.appointments = SampleDataUtil.getAppointmentSet(appointments);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Patient} that we are building.
     */
    public PatientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Patient} that we are building.
     */
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     *
     * @param url
     * @return
     */
    public PatientBuilder withMedicalRecord(String url) {
        this.medicalRecord = new MedicalRecord(url);
        return this;
    }

    public Patient build() {
        return new Patient(name, nric, phone, email, address, allergies, appointments, medicalRecord);
    }

}
