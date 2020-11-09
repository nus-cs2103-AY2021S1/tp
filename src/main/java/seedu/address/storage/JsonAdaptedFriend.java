package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Name;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.Mobile;
import seedu.address.model.friend.Passport;

/**
 * Jackson-friendly version of {@link Friend}.
 */
public class JsonAdaptedFriend {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Friend's %s field is missing!";

    private final String name;
    private final String passport;
    private final String phone;

    /**
     * Constructs a {@code JsonAdaptedFriend} with the given friend details.
     */
    @JsonCreator
    public JsonAdaptedFriend(@JsonProperty("name") String name, @JsonProperty("passport") String passport,
                             @JsonProperty("phone") String phone) {
        this.name = name;
        this.passport = passport;
        this.phone = phone;
    }

    /**
     * Converts a given {@code Friend} into this class for Jackson use.
     */
    public JsonAdaptedFriend(Friend source) {
        name = source.getName().getValue();
        passport = source.getPassport().getValue();
        phone = source.getMobile().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted friend object into the model's {@code Friend} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted friend.
     */
    public Friend toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (passport == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Passport.class.getSimpleName()));
        }
        if (!Passport.isValidPassport(passport)) {
            throw new IllegalValueException(Passport.MESSAGE_CONSTRAINTS);
        }
        final Passport modelPassport = new Passport(passport);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Mobile.class.getSimpleName()));
        }
        if (!Mobile.isValidMobile(phone)) {
            throw new IllegalValueException(Mobile.MESSAGE_CONSTRAINTS);
        }
        final Mobile modelMobile = new Mobile(phone);

        return new Friend(modelName, modelPassport, modelMobile);
    }

}
