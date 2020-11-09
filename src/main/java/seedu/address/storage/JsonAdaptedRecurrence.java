package seedu.address.storage;

import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.task.Recurrence;

public class JsonAdaptedRecurrence {
    private final Integer value;
    private final ChronoUnit unit;

    /**
     * Constructs a {code JsonAdaptedRecurrence} with the given {@code recurrence details}.
     */
    @JsonCreator
    public JsonAdaptedRecurrence(@JsonProperty("value") Integer value, @JsonProperty("chronoUnit") ChronoUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    /**
     * Converts a given {@code Recurrence} into this class for Jackson use.
     */
    public JsonAdaptedRecurrence(Recurrence source) {
        value = source.getValue();
        unit = source.getChronoUnit();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Recurrence} object.
     *
     */
    public Recurrence toModelType() {
        final Integer modelValue = value;
        final String modelUnit;

        if (unit.equals(ChronoUnit.DAYS)) {
            modelUnit = "day";
        } else if (unit.equals(ChronoUnit.WEEKS)) {
            modelUnit = "week";
        } else if (unit.equals(ChronoUnit.MONTHS)) {
            modelUnit = "month";
        } else if (unit.equals(ChronoUnit.YEARS)) {
            modelUnit = "year";
        } else {
            modelUnit = "";
        }
        return new Recurrence(modelValue, modelUnit);
    }
}
