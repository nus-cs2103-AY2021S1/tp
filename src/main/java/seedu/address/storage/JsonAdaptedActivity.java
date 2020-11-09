package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.Importance;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;

/**
 * Jackson-friendly version of {@link Activity}.
 */
public class JsonAdaptedActivity {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Activity's %s field is missing!";

    private final String name;
    private final String location;
    private final String cost;
    private final String importance;
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedActivity} with the given activity details.
     */
    @JsonCreator
    public JsonAdaptedActivity(@JsonProperty("name") String name,
                               @JsonProperty("location") String location,
                               @JsonProperty("cost") String cost,
                               @JsonProperty("levelOfImportance") String levelOfImportance,
                               @JsonProperty("dateTime") String dateTime) {
        this.name = name;
        this.location = location;
        this.cost = cost;
        this.importance = levelOfImportance;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Activity} into this class for Jackson use.
     */
    public JsonAdaptedActivity(Activity source) {
        name = source.getName().getValue();
        location = source.getLocation().getValue();
        cost = source.getCost().getValue();
        importance = source.getLevelOfImportance().getValue();
        dateTime = source.getActivityDateTime().getDateTime();
    }

    /**
     * Converts this Jackson-friendly adapted activity object into the model's {@code Activity} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted activity.
     */
    public Activity toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (cost == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName()));
        }
        if (!Cost.isValidCost(cost)) {
            throw new IllegalValueException(Cost.MESSAGE_CONSTRAINTS);
        }
        final Cost modelCost = new Cost(cost);

        if (importance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Importance.class.getSimpleName()));
        }
        if (!Importance.isValidImportance(importance)) {
            throw new IllegalValueException(Importance.MESSAGE_CONSTRAINTS);
        }
        final Importance modelImportance = new Importance(importance);

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    WanderlustDateTime.class.getSimpleName()));
        }
        if (!WanderlustDateTime.isValidWanderlustDateTime(dateTime)) {
            throw new IllegalValueException(WanderlustDateTime.MESSAGE_CONSTRAINTS);
        }
        final WanderlustDateTime modelDateTime = new WanderlustDateTime(dateTime);

        return new Activity(modelName, modelLocation, modelCost, modelImportance, modelDateTime);
    }
}
