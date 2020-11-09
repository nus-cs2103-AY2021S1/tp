package seedu.medibook.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.medicaldetail.Allergy;
import seedu.medibook.model.medicaldetail.Condition;
import seedu.medibook.model.medicaldetail.Treatment;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.Bmi;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Weight;
import seedu.medibook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_IC = "S5436781A";
    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_DOB = "08-09-1954";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_HEIGHT = "170";
    public static final String DEFAULT_WEIGHT = "58.7";
    public static final String DEFAULT_BMI = "20.3";
    public static final String DEFAULT_BLOOD_TYPE = "A+";

    private Ic ic;
    private Name name;
    private DateOfBirth dateOfBirth;
    private Phone phone;
    private Optional<Email> email;
    private Optional<Address> address;
    private Optional<Height> height;
    private Optional<Weight> weight;
    private Optional<Bmi> bmi;
    private Optional<BloodType> bloodType;
    private Set<Allergy> allergies;
    private Set<Condition> conditions;
    private Set<Treatment> treatments;
    private MedicalNoteList medicalNoteList;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        ic = new Ic(DEFAULT_IC);
        name = new Name(DEFAULT_NAME);
        dateOfBirth = new DateOfBirth(DEFAULT_DOB);
        phone = new Phone(DEFAULT_PHONE);
        email = Optional.of(new Email(DEFAULT_EMAIL));
        address = Optional.of(new Address(DEFAULT_ADDRESS));
        height = Optional.of(new Height(DEFAULT_HEIGHT));
        weight = Optional.of(new Weight(DEFAULT_WEIGHT));
        bmi = Optional.of(new Bmi(DEFAULT_BMI));
        bloodType = Optional.of(new BloodType(DEFAULT_BLOOD_TYPE));
        allergies = new HashSet<>();
        conditions = new HashSet<>();
        treatments = new HashSet<>();
        medicalNoteList = new MedicalNoteList();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        ic = patientToCopy.getIc();
        name = patientToCopy.getName();
        dateOfBirth = patientToCopy.getDateOfBirth();
        phone = patientToCopy.getPhone();
        email = patientToCopy.getEmail();
        address = patientToCopy.getAddress();
        height = patientToCopy.getHeight();
        weight = patientToCopy.getWeight();
        bmi = patientToCopy.getBmi();
        bloodType = patientToCopy.getBloodType();
        allergies = new HashSet<>(patientToCopy.getAllergies());
        conditions = new HashSet<>(patientToCopy.getConditions());
        treatments = new HashSet<>(patientToCopy.getTreatments());
        medicalNoteList = patientToCopy.getMedicalNoteList().makeCopy();
    }

    /**
     * Sets the {@code Ic} of the {@code Patient} that we are building.
     */
    public PatientBuilder withIc(String ic) {
        this.ic = new Ic(ic);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Patient} that we are building.
     */
    public PatientBuilder withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = new DateOfBirth(dateOfBirth);
        return this;
    }

    /**
     * Parses {@code allergies} into a {@code Set<Allergy>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withAllergies(String ... allergies) {
        this.allergies = SampleDataUtil.getAllergySet(allergies);
        return this;
    }

    /**
     * Parses {@code conditions} into a {@code Set<Condition>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withConditions(String ... conditions) {
        this.conditions = SampleDataUtil.getConditionSet(conditions);
        return this;
    }

    /**
     * Parses {@code treatments} into a {@code Set<Treatment>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withTreatments(String ... treatments) {
        this.treatments = SampleDataUtil.getTreatmentSet(treatments);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Patient} that we are building.
     */
    public PatientBuilder withAddress(String address) {
        if (address.isBlank()) {
            this.address = Optional.empty();
        } else {
            this.address = Optional.of(new Address(address));
        }
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
        if (email.isBlank()) {
            this.email = Optional.empty();
        } else {
            this.email = Optional.of(new Email(email));
        }
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code Patient} that we are building.
     */
    public PatientBuilder withHeight(String height) {
        if (height.isBlank()) {
            this.height = Optional.empty();
        } else {
            this.height = Optional.of(new Height(height));
        }
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code Patient} that we are building.
     */
    public PatientBuilder withWeight(String weight) {
        if (weight.isBlank()) {
            this.weight = Optional.empty();
        } else {
            this.weight = Optional.of(new Weight(weight));
        }
        return this;
    }

    /**
     * Sets the {@code Bmi} of the {@code Patient} that we are building.
     */
    public PatientBuilder withBmi(String bmi) {
        if (bmi.isBlank()) {
            this.bmi = Optional.empty();
        } else {
            this.bmi = Optional.of(new Bmi(bmi));
        }
        return this;
    }

    /**
     * Sets the {@code BloodType} of the {@code Patient} that we are building.
     */
    public PatientBuilder withBloodType(String bloodType) {
        if (bloodType.isBlank()) {
            this.bloodType = Optional.empty();
        } else {
            this.bloodType = Optional.of(new BloodType(bloodType));
        }
        return this;
    }

    /**
     * Sets the {@code MedicalNoteList} of the {@code Patient} that we are building.
     */
    public PatientBuilder withMedicalNoteList(MedicalNoteList medicalNoteList) {
        this.medicalNoteList = medicalNoteList;
        return this;
    }

    /**
     * Creates Patient object based on the fields specified in this PatientBuilder object (bmi auto-generated).
     */
    public Patient build() {
        Patient patient = new Patient(ic, name, dateOfBirth, phone, email, address, height, weight,
                bloodType, allergies, conditions, treatments);
        patient.setMedicalNoteList(medicalNoteList.makeCopy());
        return patient;
    }

    /**
     * Creates Patient object based on the fields specified in this PatientBuilder object (bmi explicitly specified).
     */
    public Patient buildWithSpecifiedBmi() {
        Patient patient = new Patient(ic, name, dateOfBirth, phone, email, address, height, weight, bmi,
                bloodType, allergies, conditions, treatments);
        patient.setMedicalNoteList(medicalNoteList.makeCopy());
        return patient;
    }

}
