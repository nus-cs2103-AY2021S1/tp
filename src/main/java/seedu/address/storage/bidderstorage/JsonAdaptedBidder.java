package seedu.address.storage.bidderstorage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.BidderId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.bidder.Bidder;

/**
 * Jackson-friendly version of {@link Bidder}.
 */
public class JsonAdaptedBidder {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String id;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedBidder(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("id") String id) {
        this.name = name;
        this.phone = phone;
        this.id = id;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedBidder(Bidder source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        id = source.getId().toString();
    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Bidder} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Bidder toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BidderId.class.getSimpleName()));
        }
        if (!BidderId.isValidId(id)) {
            throw new IllegalValueException(BidderId.MESSAGE_CONSTRAINTS);
        }
        final BidderId modelId = new BidderId(id);

        return new Bidder(modelName, modelPhone, modelId);
    }

}
