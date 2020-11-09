package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;

/**
 * Jackson-friendly version of {@link Accommodation}.
 */
public class JsonAdaptedAccommodation {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Accommodation's %s field is missing!";

    private final String name;
    private final String startDate;
    private final String endDate;
    private final String cost;
    private final String location;

    /**
     * Constructs a {@code JsonAdaptedAccommodation} with the given accommodation details.
     */
    @JsonCreator
    public JsonAdaptedAccommodation(@JsonProperty("name") String name,
                                    @JsonProperty("startDate") String startDate,
                                    @JsonProperty("endDate") String endDate,
                                    @JsonProperty("cost") String cost,
                                    @JsonProperty("location") String location) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.location = location;
    }

    /**
     * Converts a given {@code Accommodation} into this class for Jackson use.
     */
    public JsonAdaptedAccommodation(Accommodation source) {
        name = source.getName().getValue();
        startDate = source.getStartDate().getDate();
        endDate = source.getEndDate().getDate();
        cost = source.getCost().getValue();
        location = source.getLocation().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted accommodation object into the model's {@code Accommodation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted accommodation.
     */
    public Accommodation toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    WanderlustDate.class.getSimpleName()));
        }
        if (!WanderlustDate.isValidWanderlustDate(startDate)) {
            throw new IllegalValueException(WanderlustDate.MESSAGE_CONSTRAINTS);
        }
        final WanderlustDate modelStartDate = new WanderlustDate(startDate);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    WanderlustDate.class.getSimpleName()));
        }
        if (!WanderlustDate.isValidWanderlustDate(endDate)) {
            throw new IllegalValueException(WanderlustDate.MESSAGE_CONSTRAINTS);
        }
        final WanderlustDate modelEndDate = new WanderlustDate(endDate);

        if (cost == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName()));
        }
        if (!Cost.isValidCost(cost)) {
            throw new IllegalValueException(Cost.MESSAGE_CONSTRAINTS);
        }
        final Cost modelCost = new Cost(cost);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        return new Accommodation(modelName, modelStartDate, modelEndDate, modelCost, modelLocation);
    }
}
