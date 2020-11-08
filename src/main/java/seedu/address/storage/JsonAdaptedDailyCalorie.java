package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calorie.DailyCalorie;

/**
 * Jackson-friendly version of {@link DailyCalorie}.
 */
class JsonAdaptedDailyCalorie {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "DailyCalorie's %s field is missing!";
    public static final String INVALID_CALORIE_MESSAGE_FORMAT = "DailyCalorie's calorie field is incorrect!";

    private final String date;
    private final String calories;

    /**
     * Constructs a {@code JsonAdaptedDailyCalorie} with the given daily calorie details.
     */
    @JsonCreator
    public JsonAdaptedDailyCalorie(@JsonProperty("date") String date,
                              @JsonProperty("calories") String calories) {
        this.date = date;
        this.calories = calories;
    }

    /**
     * Converts a given {@code DailyCalorie} into this class for Jackson use.
     */
    public JsonAdaptedDailyCalorie(DailyCalorie source) {
        date = source.getDate().toString();
        calories = Integer.toString(source.getCalories());
    }

    /**
     * Converts this Jackson-friendly adapted daily calorie object into the model's {@code DailyCalorie} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted daily calorie.
     */
    public DailyCalorie toModelType() throws IllegalValueException {

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDate.class.getSimpleName()));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate modelLocalDate = LocalDate.parse(date, formatter);
        DailyCalorie modelDailyCalorie = new DailyCalorie(modelLocalDate);
        int modelCalories;
        try {
            modelCalories = Integer.parseInt(calories);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(INVALID_CALORIE_MESSAGE_FORMAT);
        }

        if (modelCalories < 0) {
            throw new IllegalValueException(INVALID_CALORIE_MESSAGE_FORMAT);
        }
        modelDailyCalorie.addCalories(modelCalories);
        return modelDailyCalorie;
    }
}
