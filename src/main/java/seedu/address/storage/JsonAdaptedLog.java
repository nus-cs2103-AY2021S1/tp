package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.log.Address;
import seedu.address.model.log.Email;
import seedu.address.model.log.Log;
import seedu.address.model.log.Phone;
import seedu.address.model.util.Name;

/**
 * Jackson-friendly version of {@link Log}.
 */
class JsonAdaptedLog {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Log's %s field is missing!";

    private final String exercise;
    private final String dateTime;
    private final String rep;
    private final String comment;

    /**
     * Constructs a {@code JsonAdaptedLog} with the given log details.
     */
    @JsonCreator
    public JsonAdaptedLog(@JsonProperty("exercise") String exercise, @JsonProperty("dateTime") String dateTime,
                          @JsonProperty("rep") String rep, @JsonProperty("comment") String comment) {
        this.exercise = exercise;
        this.dateTime = dateTime;
        this.rep = rep;
        this.comment = comment;
    }

    /**
     * Converts a given {@code Log} into this class for Jackson use.
     */
    public JsonAdaptedLog(Log source) {
        exercise = source.getExercise().getName().toString();
        dateTime = source.getDateTime().toString();
        rep = source.getReps().value;
        comment = source.getComment().value;
    }

    /**
     * Converts this Jackson-friendly adapted log object into the model's {@code Log} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted log.
     * TODO: Refine this later
     */
    public Log toModelType() throws IllegalValueException {
        if (exercise == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(exercise)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(dateTime)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(dateTime);

        if (rep == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(rep)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(rep);

        if (comment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(comment)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(comment);

        return new Log(null, null, null, null);
    }

}
