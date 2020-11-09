package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;

/**
 * Jackson-friendly version of {@link ItineraryAttraction}.
 */
class JsonAdaptedItineraryAttraction {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Itinerary attraction's %s field is missing!";

    private final JsonAdaptedAttraction attraction;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedItineraryAttraction} with the given itinerary attraction details.
     */
    @JsonCreator
    public JsonAdaptedItineraryAttraction(@JsonProperty("attraction") JsonAdaptedAttraction attraction,
                                          @JsonProperty("startTime") String startTime,
                                          @JsonProperty("endTime") String endTime) {
        this.attraction = attraction;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code ItineraryAttraction} into this class for Jackson use.
     */
    public JsonAdaptedItineraryAttraction(ItineraryAttraction source) {
        attraction = new JsonAdaptedAttraction(source.getAttraction());
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted itinerary attraction object into the model's {@code ItineraryAttraction}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted itinerary attraction.
     */
    public ItineraryAttraction toModelType() throws IllegalValueException {
        final Attraction modelAttraction;
        final ItineraryTime modelStartTime;
        final ItineraryTime modelEndTime;

        // Start time is not optional
        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ItineraryTime.class.getSimpleName()));
        } else if (!ItineraryTime.isValidItineraryTime(startTime)) {
            throw new IllegalValueException(ItineraryTime.MESSAGE_CONSTRAINTS);
        } else {
            modelStartTime = new ItineraryTime(startTime);
        }

        // End time is not optional
        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ItineraryTime.class.getSimpleName()));
        } else if (!ItineraryTime.isValidItineraryTime(endTime)) {
            throw new IllegalValueException(ItineraryTime.MESSAGE_CONSTRAINTS);
        } else {
            modelEndTime = new ItineraryTime(endTime);
        }

        // Attraction is not optional
        if (attraction == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Attraction.class.getSimpleName()));
        } else {
            modelAttraction = attraction.toModelType();
        }

        return new ItineraryAttraction(modelAttraction, modelStartTime, modelEndTime);
    }
}
