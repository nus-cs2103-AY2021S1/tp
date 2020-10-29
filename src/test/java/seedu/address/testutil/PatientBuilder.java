package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.ProfilePicture;
import seedu.address.model.patient.Sex;
import seedu.address.model.tag.ColorTag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ICNUMBER = "S1234567A";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_PROFILE_PICTURE = "data/stock_picture.png";
    public static final String DEFAULT_SEX = "F";
    public static final String DEFAULT_BLOODTYPE = "A+";
    public static final VisitHistory DEFAULT_VISIT_HISTORY = new VisitHistory(new ArrayList<Visit>());

    private Name name;
    private Phone phone;
    private IcNumber icNumber;
    private Address address;
    private Email email;
    private ProfilePicture profilePicture;
    private Sex sex;
    private BloodType bloodType;
    private Set<Allergy> allergies;
    private ColorTag colorTag;
    private VisitHistory visitHistory;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        icNumber = new IcNumber(DEFAULT_ICNUMBER);
        address = new Address(DEFAULT_ADDRESS);
        email = new Email(DEFAULT_EMAIL);
        profilePicture = new ProfilePicture(DEFAULT_PROFILE_PICTURE);
        sex = new Sex(DEFAULT_SEX);
        bloodType = new BloodType(DEFAULT_BLOODTYPE);
        allergies = new HashSet<>();
        colorTag = new ColorTag();
        visitHistory = DEFAULT_VISIT_HISTORY;
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        phone = patientToCopy.getPhone();
        icNumber = patientToCopy.getIcNumber();
        address = patientToCopy.getAddress();
        email = patientToCopy.getEmail();
        profilePicture = patientToCopy.getProfilePicture();
        sex = patientToCopy.getSex();
        bloodType = patientToCopy.getBloodType();
        allergies = new HashSet<>(patientToCopy.getAllergies());
        colorTag = patientToCopy.getColorTag();
        visitHistory = patientToCopy.getVisitHistory();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code IcNumber} of the {@code Patient} that we are building.
     */
    public PatientBuilder withIcNumber(String icNumber) {
        this.icNumber = new IcNumber(icNumber);
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
     * Sets the {@code Email} of the {@code Patient} that we are building.
     */
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Profile Picture} of the {@code Patient} that we are building.
     */
    public PatientBuilder withProfilePicture(String profilePic) {
        this.profilePicture = new ProfilePicture(profilePic);
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code Patient} that we are building.
     */
    public PatientBuilder withSex(String sex) {
        this.sex = new Sex(sex);
        return this;
    }

    /**
     * Sets the {@code BloodType} of the {@code Patient} that we are building.
     */
    public PatientBuilder withBloodType(String bloodType) {
        this.bloodType = new BloodType(bloodType);
        return this;
    }

    /**
     * Parses the {@code Allergy} into a {@code Set<Allergy>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withAllergies(String ... allergies) {
        this.allergies = SampleDataUtil.getAllergySet(allergies);
        return this;
    }

    /**
     * Sets the {@code ColorTag} of the {@code Patient} that we are building.
     */
    public PatientBuilder withColorTag(String colorTag) {
        this.colorTag = new ColorTag(colorTag);
        return this;
    }

    /**
     * Sets the {@code VisitHistory} of the {@code Patient} that we are building.
     */
    public PatientBuilder withVisitHistory(VisitHistory visitHistory) {
        this.visitHistory = visitHistory;
        return this;
    }

    /**
     * Builds {@code Patient} with the given fields.
     */
    public Patient build() {
        return new Patient(name, phone, icNumber, visitHistory, address, email, profilePicture,
                sex, bloodType, allergies, colorTag);
    }

}
