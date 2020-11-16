package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.consultation.Address;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.Day;
import seedu.address.model.consultation.Time;
import seedu.address.model.consultation.Type;
import seedu.address.model.person.Name;

/**
 * Jackson-friendly version of {@link Consultation}.
 */
public class JsonAdaptedConsultation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consultation's %s field is missing!";

    private final String name;
    private final String date;
    private final String time;
    private final String location;
    private final String type;

    /**
     * Constructs a {@code JsonAdaptedConsultation} with the given consultation details.
     *
     * @param name Name of consultation requestor.
     * @param date Date of consultation.
     * @param time Time of consultation.
     * @param location Location of consultation.
     * @param type Type of consultation.
     */
    @JsonCreator
    public JsonAdaptedConsultation(@JsonProperty("name") String name, @JsonProperty("date") String date,
                                   @JsonProperty("time") String time, @JsonProperty("location") String location,
                                   @JsonProperty("type") String type) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.type = type;
    }

    /**
     * Converts a given {@code Consultation} into this class for Jackson use.
     */
    public JsonAdaptedConsultation(Consultation source) {
        assert source != null;
        name = source.getName().fullName;
        date = source.getDate().toString();
        time = source.getTime().toString();
        location = source.getLocation().value;
        type = source.getType().type.toString();
    }

    /**
     * Converts this Jackson-friendly adapted consultation object into the model's {@code Consultation} object.
     *
     * @return converted Consultation.
     * @throws IllegalValueException if there were any data constraints violated in the adapted consultation.
     */
    public Consultation toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDay(date)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day modelDay = Day.fromDateString(date);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelTime = Time.fromTimeString(time);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        final Address modelLocation = new Address(location);

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Type.class.getSimpleName()));
        }

        final Type modelType = new Type(type);

        return new Consultation(modelName,
                modelDay,
                modelTime,
                modelLocation,
                modelType);
    }
}
