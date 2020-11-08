package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.profile.Profile;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Phone;

public class JsonSerializableProfileManager {
    private final JsonAdaptedProfile jsonAdaptedProfile;

    /**
     * Constructs a {@code JsonSerializableProfileManager} with the given menu items.
     */
    @JsonCreator
    public JsonSerializableProfileManager(
            @JsonProperty("jsonAdaptedProfile") JsonAdaptedProfile jsonAdaptedProfile
    ) {
        this.jsonAdaptedProfile = new JsonAdaptedProfile(
                jsonAdaptedProfile.getAddress(),
                jsonAdaptedProfile.getPhone()
        );
    }

    /**
     * Converts a given {@code Profile} into this class for Jackson use.
     */
    public JsonSerializableProfileManager(Profile profile) {
        this.jsonAdaptedProfile = new JsonAdaptedProfile(profile);
    }

    /**
     * Converts this address book into the model's {@code Profile} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Profile toModelType() {
        return new Profile(new Phone(jsonAdaptedProfile.getPhone()), new Address(jsonAdaptedProfile.getAddress()));
    }
}
