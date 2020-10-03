package seedu.address.testutil;

import seedu.address.model.exercise.Exercise;
import seedu.address.model.util.Name;

/**
 * A utility class to help with building Exercise objects.
 */
public class ExerciseBuilder {

    public static final String DEFAULT_NAME = "Star fish jumps";
    public static final int DEFAULT_CALORIESPERREP = 30;

    private Name name;
    private int caloriesPerRep;

    /**
     * Creates a {@code ExerciseBuilder} with the default details.
     */
    public ExerciseBuilder() {
        name = new Name(DEFAULT_NAME);
        caloriesPerRep = DEFAULT_CALORIESPERREP;
    }

    /**
     * Initializes the ExerciseBuilder with the data of {@code exerciseToCopy}.
     */
    public ExerciseBuilder(Exercise exerciseToCopy) {
        name = exerciseToCopy.getName();
        caloriesPerRep = exerciseToCopy.getCaloriesPerRep();
    }

    /**
     * Sets the {@code Name} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withCaloriesPerRep(int caloriesPerRep) {
        this.caloriesPerRep = caloriesPerRep;
        return this;
    }

    public Exercise build() {
        return new Exercise(name, caloriesPerRep);
    }

}
