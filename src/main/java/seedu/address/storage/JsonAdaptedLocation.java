package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.location.Location;


/**
 * Jackson-friendly version of {@link Location}.
 */
class JsonAdaptedLocation {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Location's %s field is missing!";

    // Identity fields
    private final int id;
    private final String name;

    /**
     * Constructs a {@code JsonAdaptedLocation} with the given item details.
     */
    @JsonCreator
    public JsonAdaptedLocation(@JsonProperty("id") int id,
                               @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Converts a given {@code Location} into this class for Jackson use.
     */
    public JsonAdaptedLocation(Location source) {
        id = source.getId();
        name = source.getName();
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Location} object.
     */
    public Location toModelType() {
        return new Location(name);
    }

}
