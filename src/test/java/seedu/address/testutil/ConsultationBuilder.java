package seedu.address.testutil;

import seedu.address.model.consultation.Address;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.Day;
import seedu.address.model.consultation.Time;
import seedu.address.model.consultation.Type;
import seedu.address.model.person.Name;

/**
 * A utility class to help with building Consultation objects.
 */
public class ConsultationBuilder {
    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_DATE = "20/10/2020";
    public static final String DEFAULT_TIME = "12:00";
    public static final String DEFAULT_LOCATION = "SOC Basement";
    public static final String DEFAULT_TYPE = "personal";

    private Name name;
    private Day date;
    private Time time;
    private Address location;
    private Type type;

    /**
     * Creates a {@code ConsultationBuilder} with the default details.
     */
    public ConsultationBuilder() {
        name = new Name(DEFAULT_NAME);
        date = Day.fromDateString(DEFAULT_DATE);
        time = Time.fromTimeString(DEFAULT_TIME);
        location = new Address(DEFAULT_LOCATION);
        type = new Type(DEFAULT_TYPE);
    }


    /**
     * Initializes the ConsultationBuilder with the data of {@code consult}.
     */
    public ConsultationBuilder(Consultation consult) {
        name = consult.getName();
        date = consult.getDate();
        time = consult.getTime();
        location = consult.getLocation();
        type = consult.getType();
    }

    /**
     * Sets the {@code Name} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Day} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withDay(String date) {
        this.date = Day.fromDateString(date);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withTime(String time) {
        this.time = Time.fromTimeString(time);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withLocation(String location) {
        this.location = new Address(location);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withType(String type) {
        this.type = new Type(type);
        return this;
    }

    /**
     * Class builder.
     * @return Consultation
     */
    public Consultation build() {
        return new Consultation(name, date, time, location, type);

    }
}
