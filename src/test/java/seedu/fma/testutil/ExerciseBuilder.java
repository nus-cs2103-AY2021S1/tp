package seedu.fma.testutil;

import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;

import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;


/**
 * A utility class to help with building Exercise objects.
 */
public class ExerciseBuilder {
    public static final Exercise SAMPLE_EXERCISE = getSampleExercises()[0];
    public static final String DEFAULT_EXERCISE_NAME = SAMPLE_EXERCISE.getName().toString();
    public static final Calories DEFAULT_CALORIES_PER_REP = SAMPLE_EXERCISE.getCaloriesPerRep();

    private Name name;
    private Calories caloriesPerRep;

    /**
     * Creates a {@code ExerciseBuilder} with the default details.
     */
    public ExerciseBuilder() {
        name = new Name(DEFAULT_EXERCISE_NAME);
        caloriesPerRep = DEFAULT_CALORIES_PER_REP;
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
    public ExerciseBuilder withStringName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withName(Name name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code caloriesPerRep} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withCaloriesPerRep(Calories caloriesPerRep) {
        this.caloriesPerRep = caloriesPerRep;
        return this;
    }

    public Exercise build() {
        return new Exercise(name, caloriesPerRep);
    }

}
