package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.LocationList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.location.Location;

/**
 * An Immutable LocationList that is serializable to JSON format.
 */
@JsonRootName(value = "locationlist")
class JsonSerializableLocationList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Locations list contains duplicate location(s).";

    private final List<JsonAdaptedLocation> locations = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLocationList} with the given locations.
     */
    @JsonCreator
    public JsonSerializableLocationList(@JsonProperty("locations") List<JsonAdaptedLocation> locations) {
        this.locations.addAll(locations);
    }

    /**
     * Converts a given {@code ReadOnlyLocationList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLocationList}.
     */
    public JsonSerializableLocationList(ReadOnlyLocationList source) {
        locations.addAll(source.getLocationList().stream().map(JsonAdaptedLocation::new).collect(Collectors.toList()));
    }

    /**
     * Converts this location list into the model's {@code LocationList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LocationList toModelType() throws IllegalValueException {
        LocationList locationList = new LocationList();
        for (JsonAdaptedLocation jsonAdaptedLocation : locations) {
            Location location = jsonAdaptedLocation.toModelType();
            if (locationList.hasLocation(location)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            locationList.addLocation(location);
        }
        return locationList;
    }

}
