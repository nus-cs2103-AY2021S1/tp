package seedu.address.storage.sellerstorage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.SellerId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.seller.Seller;

/**
 * Jackson-friendly version of {@link Seller}.
 */
public class JsonAdaptedSeller {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String id;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedSeller(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("id") String id) {
        this.name = name;
        this.phone = phone;
        this.id = id;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedSeller(Seller source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        id = source.getId().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Seller} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Seller toModelType() throws IllegalValueException {

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
                    SellerId.class.getSimpleName()));
        }
        if (!SellerId.isValidId(id)) {
            throw new IllegalValueException(SellerId.MESSAGE_CONSTRAINTS);
        }
        final SellerId modelId = new SellerId(id);

        return new Seller(modelName, modelPhone, modelId);
    }

}
