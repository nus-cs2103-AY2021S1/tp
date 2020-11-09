package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * The temperature range class serves to update a list of patients that fall within the temperature range.
 * A temperature range object contains two immutable temperature objects for comparison.
 */
public class TemperatureRange {
    public static final String MESSAGE_CONSTRAINTS_TEMPERATURERANGE =
            "Temperature range should contain temperature-temperature";
    public static final String MESSAGE_CONSTRAINTS_TEMPERATURE =
            "Temperature should only contain numbers, and it should be to one decimal place. (E.g. 36.0)";
    public static final String VALIDATION_REGEX_TEMPERATURE = "\\d\\d[.]\\d";
    public static final String VALIDATION_REGEX_TEMPERATURERANGE =
            VALIDATION_REGEX_TEMPERATURE + "[-]" + VALIDATION_REGEX_TEMPERATURE;
    public final double startingTemperature;
    public final double endingTemperature;

    /**
     * Constructs a {@code TemperatureRange}.
     * @param temperatureRange A valid temperature range.
     */
    public TemperatureRange (String temperatureRange) {
        requireNonNull(temperatureRange);
        checkArgument(isValidTemperatureRange(temperatureRange), MESSAGE_CONSTRAINTS_TEMPERATURERANGE);
        String[] temperatures = temperatureRange.split("-");
        checkArgument(isValidTemperature(temperatures[0]), MESSAGE_CONSTRAINTS_TEMPERATURE);
        //ensures temp is always to 1 d.p.
        checkArgument(isValidTemperature(temperatures[0]), MESSAGE_CONSTRAINTS_TEMPERATURE);
        //ensures temp is always to 1 d.p.
        startingTemperature = Double.parseDouble(temperatures[0]);
        endingTemperature = Double.parseDouble(temperatures[1]);

    }

    /**
     * Returns the starting temperature for the temperature range .
     */
    public double getStartingTemperature () {
        return this.startingTemperature;
    }

    /**
     * Returns the ending temperature for the temperature range .
     */
    public double getEndingTemperature () {
        return this.endingTemperature;
    }

    /**
     * Returns true if a given string is a valid temperature.
     */
    public static boolean isValidTemperature(String test) {
        return test.matches(VALIDATION_REGEX_TEMPERATURE);
    }

    /**
     * Returns true if a given string is a valid temperature Range .
     */
    public static boolean isValidTemperatureRange(String test) {
        return test.matches(VALIDATION_REGEX_TEMPERATURERANGE);
    }

    @Override
    public String toString() {
        return startingTemperature + " to " + endingTemperature;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TemperatureRange // instanceof handles nulls
                && startingTemperature == ((TemperatureRange) other).startingTemperature
                && endingTemperature == ((TemperatureRange) other).endingTemperature); // state check
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}

