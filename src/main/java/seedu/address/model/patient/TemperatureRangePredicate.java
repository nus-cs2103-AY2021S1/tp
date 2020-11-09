package seedu.address.model.patient;

import java.util.function.Predicate;

/**
 * Tests that a {@code Patient}'s {@code Temperature} falls within the temperature range.
 */
public class TemperatureRangePredicate implements Predicate<Patient> {
    private final double startingTemperature;
    private final double endingTemperature;

    /**
     * Constructs a {@code TemperatureRangePredicate}.
     * @param startingTemperature The start of temperature range.
     * @param endingTemperature The end of temperature range.
     */
    public TemperatureRangePredicate(double startingTemperature, double endingTemperature) {
        this.startingTemperature = startingTemperature;
        this.endingTemperature = endingTemperature;
    }

    @Override
    public boolean test(Patient patient) {
        double temperature = patient.getTemperature().getValue();
        return startingTemperature <= temperature && endingTemperature >= temperature;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TemperatureRangePredicate // instanceof handles nulls
                && startingTemperature == (((TemperatureRangePredicate) other).startingTemperature) // state check
                && endingTemperature == (((TemperatureRangePredicate) other).endingTemperature)); // state check
    }
}
