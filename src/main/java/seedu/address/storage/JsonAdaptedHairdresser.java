package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;
import seedu.address.model.person.hairdresser.Title;
import seedu.address.model.specialisation.Specialisation;

/**
 * Jackson-friendly version of {@link Hairdresser}.
 */

public class JsonAdaptedHairdresser extends JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Hairdresser's %s field is missing!";

    private final String title;
    private final List<JsonAdaptedSpecialisation> specs = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedHairdresser} with the given hairdresser details.
     */
    @JsonCreator
    public JsonAdaptedHairdresser(@JsonProperty("id") String id,
                                  @JsonProperty("name") String name,
                                  @JsonProperty("phone") String phone,
                                  @JsonProperty("email") String email,
                                  @JsonProperty("gender") String gender,
                                  @JsonProperty("title") String title,
                                  @JsonProperty("specs") List<JsonAdaptedSpecialisation> specs) {
        super(id, name, phone, email, gender);
        this.title = title;
        if (specs != null) {
            this.specs.addAll(specs);
        }
    }

    /**
     * Converts a given {@code Hairdresser} into this class for Jackson use.
     */
    public JsonAdaptedHairdresser(Hairdresser source) {
        super(String.valueOf(source.getId()),
                source.getName().fullName,
                source.getPhone().value,
                source.getEmail().value,
                source.getGender().toString());
        title = source.getTitle().value;
        specs.addAll(source.getSpecs().stream()
                .map(JsonAdaptedSpecialisation::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Hairdresser} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Hairdresser toModelType() throws IllegalValueException {
        String hid = getId();
        if (hid == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    HairdresserId.class.getSimpleName()));
        }
        if (!HairdresserId.isValidId(hid)) {
            throw new IllegalValueException(HairdresserId.MESSAGE_CONSTRAINTS);
        }
        final HairdresserId modelHid = new HairdresserId(hid);

        String name = getName();
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        String phone = getPhone();
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        String email = getEmail();
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        String gender = getGender();
        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        final List<Specialisation> hairdresserSpecs = new ArrayList<>();
        for (JsonAdaptedSpecialisation spec : specs) {
            hairdresserSpecs.add(spec.toModelType());
        }
        final Set<Specialisation> modelSpecs = new HashSet<>(hairdresserSpecs);

        return new Hairdresser(modelHid, modelName, modelPhone, modelEmail, modelGender, modelTitle, modelSpecs);
    }
}
