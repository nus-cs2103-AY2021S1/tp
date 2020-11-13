package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.Weight;

/**
 * Jackson-friendly version of {@link Day}.
 */
class JsonAdaptedDay {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Day's %s field is missing!";

    private final String date;
    private final String weight;
    private final JsonAdaptedCalorieManager calorieManager;


    /**
     * Constructs a {@code JsonAdaptedDay} with the given day details.
     */
    @JsonCreator
    public JsonAdaptedDay(@JsonProperty("date") String date,
                          @JsonProperty("weight") String weight,
                          @JsonProperty("calorie manager") JsonAdaptedCalorieManager calorieManager) {
        this.date = date;
        this.weight = weight;
        this.calorieManager = calorieManager;
    }

    /**
     * Converts a given {@code Day} into this class for Jackson use.
     */
    public JsonAdaptedDay(Day source) {
        date = source.getDate().value;
        weight = source.getWeight().value;
        calorieManager = new JsonAdaptedCalorieManager(source.getCalorieManager());
    }

    /**
     * Converts this Jackson-friendly adapted day object into the model's {@code Day} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted day.
     */
    public Day toModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        final Weight modelWeight = new Weight(weight);

        if (calorieManager == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CalorieManager.class.getSimpleName()));
        }
        final CalorieManager modelCalorieManager = calorieManager.toModelType();

        return new Day(modelDate, modelWeight, modelCalorieManager);
    }

}
