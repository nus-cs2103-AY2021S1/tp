package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;


/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;
    private final String nric;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedAllergy> allergy = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointed = new ArrayList<>();
    private final String medicalRecordUrl;

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("nric") String nric,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("allergy") List<JsonAdaptedAllergy> allergy,
                              @JsonProperty("appointed") List<JsonAdaptedAppointment> appointed,
                              @JsonProperty("medicalRecordUrl") String medicalRecordUrl) {
        this.name = name;
        this.nric = nric;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.medicalRecordUrl = medicalRecordUrl;
        if (allergy != null) {
            this.allergy.addAll(allergy);
        }
        if (appointed != null) {
            this.appointed.addAll(appointed);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        nric = source.getNric().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        allergy.addAll(source.getAllergies().stream()
                .map(JsonAdaptedAllergy::new)
                .collect(Collectors.toList()));
        appointed.addAll(source.getAppointments().stream()
                .map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
        medicalRecordUrl = source.getMedicalRecord().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Allergy> personAllergies = new ArrayList<>();
        final List<Appointment> personAppointments = new ArrayList<>();
        for (JsonAdaptedAllergy allergy : allergy) {
            personAllergies.add(allergy.toModelType());
        }
        for (JsonAdaptedAppointment appointment: appointed) {
            personAppointments.add(appointment.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }

        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!email.isEmpty() && !Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (medicalRecordUrl == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MedicalRecord.class.getSimpleName()));
        }
        if (!MedicalRecord.isValidUrl(medicalRecordUrl)) {
            throw new IllegalValueException(MedicalRecord.MESSAGE_CONSTRAINTS);
        }
        final MedicalRecord modelMedicalRecord = new MedicalRecord(medicalRecordUrl);

        final Set<Allergy> modelAllergies = new HashSet<>(personAllergies);
        final Set<Appointment> modelAppointments = new HashSet<>(personAppointments);
        return new Patient(modelName, modelNric, modelPhone, modelEmail, modelAddress,
                modelAllergies, modelAppointments, modelMedicalRecord);
    }

}
