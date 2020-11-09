package seedu.address.testutil;


import seedu.address.model.patient.Age;
import seedu.address.model.patient.Comment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PeriodOfStay;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Temperature;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_TEMPERATURE = "36.8";
    public static final String DEFAULT_PERIOD = "20201001-20201014";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_AGE = "37";
    public static final String DEFAULT_COMMENT = "-";

    private Name name;
    private Temperature temperature;
    private PeriodOfStay periodOfStay;
    private Phone phone;
    private Age age;
    private Comment comment;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        temperature = new Temperature(DEFAULT_TEMPERATURE);
        periodOfStay = new PeriodOfStay(DEFAULT_PERIOD);
        phone = new Phone(DEFAULT_PHONE);
        age = new Age(DEFAULT_AGE);
        comment = new Comment(DEFAULT_COMMENT);
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        temperature = patientToCopy.getTemperature();
        periodOfStay = patientToCopy.getPeriodOfStay();
        phone = patientToCopy.getPhone();
        age = patientToCopy.getAge();
        comment = patientToCopy.getComment();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Temperature} of the {@code Patient} that we are building.
     */
    public PatientBuilder withTemperature(String temperature) {
        this.temperature = new Temperature(temperature);
        return this;
    }

    /**
     * Sets the {@code PeriodOfStay} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPeriodOfStay(String periodOfStay) {
        this.periodOfStay = new PeriodOfStay(periodOfStay);
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
     * Sets the {@code Age} of the {@code Patient} that we are building.
     */
    public PatientBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Comment} of the {@code Patient} that we are building.
     */
    public PatientBuilder withComment(String comment) {
        this.comment = new Comment(comment);
        return this;
    }

    public Patient build() {
        return new Patient(name, temperature, periodOfStay, phone, age, comment);
    }
}
