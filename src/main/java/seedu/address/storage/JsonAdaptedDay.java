package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * Jackson-friendly version of {@link Day}.
 */
public class JsonAdaptedDay {

    private final int day;
    private final List<JsonAdaptedItineraryAttraction> itineraryAttractions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDay} with the given day details.
     */
    @JsonCreator
    public JsonAdaptedDay(@JsonProperty("day") int day,
                          @JsonProperty("itineraryAttractions")
                                  List<JsonAdaptedItineraryAttraction> itineraryAttractions) {
        this.day = day;
        if (itineraryAttractions != null) {
            this.itineraryAttractions.addAll(itineraryAttractions);
        }
    }

    /**
     * Converts a given {@code Day} into this class for Jackson use.
     */
    public JsonAdaptedDay(Day source) {
        day = source.value;
        itineraryAttractions.addAll(source.getItineraryAttractions().stream()
                .map(JsonAdaptedItineraryAttraction::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Day toModelType() throws IllegalValueException {
        final List<ItineraryAttraction> modelItineraryAttractions = new ArrayList<>();

        if (!Day.isValidDayNumber(day)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }

        for (JsonAdaptedItineraryAttraction itineraryAttraction : itineraryAttractions) {
            modelItineraryAttractions.add(itineraryAttraction.toModelType());
        }

        return new Day(day, modelItineraryAttractions);
    }
}
