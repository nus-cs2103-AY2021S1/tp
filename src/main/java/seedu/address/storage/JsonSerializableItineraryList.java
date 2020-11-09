package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ItineraryList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.itinerary.Itinerary;

/**
 * An Immutable ItineraryList that is serializable to JSON format.
 */
@JsonRootName(value = "itinerarylist")
class JsonSerializableItineraryList {

    public static final String MESSAGE_DUPLICATE_ITINERARY = "Itinerary list contains duplicate itineraries.";

    private final List<JsonAdaptedItinerary> itineraries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableItineraryList} with the given itineraries.
     */
    @JsonCreator
    public JsonSerializableItineraryList(@JsonProperty("itineraries") List<JsonAdaptedItinerary> itineraries) {
        this.itineraries.addAll(itineraries);
    }

    /**
     * Converts a given {@code ReadOnlyItineraryList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableItineraryList}.
     */
    public JsonSerializableItineraryList(ReadOnlyItineraryList source) {
        itineraries.addAll(source.getItineraryList().stream().map(JsonAdaptedItinerary::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this itinerary list into the model's {@code ItineraryList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ItineraryList toModelType() throws IllegalValueException {
        ItineraryList itineraryList = new ItineraryList();
        for (JsonAdaptedItinerary jsonAdaptedItinerary : itineraries) {
            Itinerary itinerary = jsonAdaptedItinerary.toModelType();
            if (itineraryList.hasItinerary(itinerary)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITINERARY);
            }
            itineraryList.addItinerary(itinerary);
        }
        return itineraryList;
    }

}
