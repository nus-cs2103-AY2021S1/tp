package seedu.medibook.storage;

import static seedu.medibook.model.patient.Patient.OPTIONAL_FIELD_EMPTY_MESSAGE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.medicaldetail.Allergy;
import seedu.medibook.model.medicaldetail.Condition;
import seedu.medibook.model.medicaldetail.Treatment;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.Bmi;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Record;
import seedu.medibook.model.patient.Weight;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String ic;
    private final String name;
    private final String dateOfBirth;
    private final String phone;
    private final String email;
    private final String address;
    private final String height;
    private final String weight;
    private final String bmi;
    private final String bloodType;
    private final JsonAdaptedRecord record;
    private final List<JsonAdaptedAllergy> allergies = new ArrayList<>();
    private final List<JsonAdaptedCondition> conditions = new ArrayList<>();
    private final List<JsonAdaptedTreatment> treatments = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("ic") String ic, @JsonProperty("name") String name,
                              @JsonProperty("dateOfBirth") String dateOfBirth, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("height") String height, @JsonProperty("weight") String weight,
                              @JsonProperty("bmi") String bmi, @JsonProperty("blood type") String bloodType,
                              @JsonProperty("record") JsonAdaptedRecord record,
                              @JsonProperty("allergies") List<JsonAdaptedAllergy> allergies,
                              @JsonProperty("conditions") List<JsonAdaptedCondition> conditions,
                              @JsonProperty("treatments") List<JsonAdaptedTreatment> treatments) {
        this.ic = ic;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bloodType = bloodType;
        this.record = record;

        if (allergies != null) {
            this.allergies.addAll(allergies);
        }
        if (conditions != null) {
            this.conditions.addAll(conditions);
        }
        if (treatments != null) {
            this.treatments.addAll(treatments);
        }
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        ic = source.getIc().ic;
        name = source.getName().fullName;
        dateOfBirth = source.getDateOfBirthInputString();
        phone = source.getPhone().value;

        // email
        if (source.getEmail().isPresent()) {
            email = source.getStringEmail();
        } else {
            email = OPTIONAL_FIELD_EMPTY_MESSAGE;
        }

        // address
        if (source.getAddress().isPresent()) {
            address = source.getStringAddress();
        } else {
            address = OPTIONAL_FIELD_EMPTY_MESSAGE;
        }

        // height
        if (source.getHeight().isPresent()) {
            height = source.getStringHeight();
        } else {
            height = OPTIONAL_FIELD_EMPTY_MESSAGE;
        }

        // weight
        if (source.getWeight().isPresent()) {
            weight = source.getStringWeight();
        } else {
            weight = OPTIONAL_FIELD_EMPTY_MESSAGE;
        }

        // bmi
        if (source.getBmi().isPresent()) {
            bmi = source.getStringBmi();
        } else {
            bmi = OPTIONAL_FIELD_EMPTY_MESSAGE;
        }

        // blood type
        if (source.getBloodType().isPresent()) {
            bloodType = source.getStringBloodType();
        } else {
            bloodType = OPTIONAL_FIELD_EMPTY_MESSAGE;
        }

        record = new JsonAdaptedRecord(source.getRecord());

        allergies.addAll(source.getAllergies().stream()
            .map(JsonAdaptedAllergy::new)
            .collect(Collectors.toList()));

        conditions.addAll(source.getConditions().stream()
            .map(JsonAdaptedCondition::new)
            .collect(Collectors.toList()));

        treatments.addAll(source.getTreatments().stream()
            .map(JsonAdaptedTreatment::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Allergy> patientAllergies = new ArrayList<>();
        for (JsonAdaptedAllergy allergy : allergies) {
            patientAllergies.add(allergy.toModelType());
        }

        final List<Condition> patientConditions = new ArrayList<>();
        for (JsonAdaptedCondition condition : conditions) {
            patientConditions.add(condition.toModelType());
        }

        final List<Treatment> patientTreatments = new ArrayList<>();
        for (JsonAdaptedTreatment tag : treatments) {
            patientTreatments.add(tag.toModelType());
        }

        if (ic == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName()));
        }
        if (!Ic.isValidIc(ic)) {
            throw new IllegalValueException(Ic.MESSAGE_CONSTRAINTS);
        }
        final Ic modelIc = new Ic(ic);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (dateOfBirth == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfBirth.class.getSimpleName()));
        }
        if (!DateOfBirth.isValidDateOfBirth(dateOfBirth)) {
            throw new IllegalValueException(DateOfBirth.MESSAGE_CONSTRAINTS);
        }
        final DateOfBirth modelDateOfBirth = new DateOfBirth(dateOfBirth);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        final Optional<Email> modelEmail;

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }

        if (email.equals(OPTIONAL_FIELD_EMPTY_MESSAGE)) {
            modelEmail = Optional.empty();
        } else if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        } else {
            modelEmail = Optional.of(new Email(email));
        }

        final Optional<Address> modelAddress;

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }

        if (address.equals(OPTIONAL_FIELD_EMPTY_MESSAGE)) {
            modelAddress = Optional.empty();
        } else if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        } else {
            modelAddress = Optional.of(new Address(address));
        }

        final Optional<Height> modelHeight;

        if (height == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }

        if (height.equals(OPTIONAL_FIELD_EMPTY_MESSAGE)) {
            modelHeight = Optional.empty();
        } else if (!Height.isValidHeight(height)) {
            throw new IllegalValueException(Height.MESSAGE_CONSTRAINTS);
        } else {
            modelHeight = Optional.of(new Height(height));
        }

        final Optional<Weight> modelWeight;

        if (weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }

        if (weight.equals(OPTIONAL_FIELD_EMPTY_MESSAGE)) {
            modelWeight = Optional.empty();
        } else if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        } else {
            modelWeight = Optional.of(new Weight(weight));
        }

        final Optional<Bmi> modelBmi;

        if (bmi == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Bmi.class.getSimpleName()));
        }

        if (bmi.equals(OPTIONAL_FIELD_EMPTY_MESSAGE)) {
            modelBmi = Optional.empty();
        } else if (!Bmi.isValidBmi(bmi)) {
            throw new IllegalValueException(Bmi.MESSAGE_CONSTRAINTS);
        } else {
            modelBmi = Optional.of(new Bmi(bmi));
        }

        final Optional<BloodType> modelBloodType;

        if (bloodType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BloodType.class.getSimpleName()));
        }

        if (bloodType.equals(OPTIONAL_FIELD_EMPTY_MESSAGE)) {
            modelBloodType = Optional.empty();
        } else if (!BloodType.isValidBloodType(bloodType)) {
            throw new IllegalValueException(BloodType.MESSAGE_CONSTRAINTS);
        } else {
            modelBloodType = Optional.of(new BloodType(bloodType));
        }

        final Set<Allergy> modelAllergies = new HashSet<>(patientAllergies);
        final Set<Condition> modelConditions = new HashSet<>(patientConditions);
        final Set<Treatment> modelTreatments = new HashSet<>(patientTreatments);

        final Patient modelPatient = new Patient(modelIc, modelName, modelDateOfBirth, modelPhone, modelEmail,
                modelAddress, modelHeight, modelWeight, modelBmi, modelBloodType,
                modelAllergies, modelConditions, modelTreatments);

        if (record == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Record.class.getSimpleName()));
        }

        final Record modelRecord = record.toModelType();

        modelPatient.setRecord(modelRecord);

        return modelPatient;
    }

}
