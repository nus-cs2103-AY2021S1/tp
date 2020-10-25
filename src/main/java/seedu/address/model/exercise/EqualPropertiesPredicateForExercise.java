package seedu.address.model.exercise;

import java.util.function.Predicate;

public class EqualPropertiesPredicateForExercise implements Predicate<Exercise> {
    private final Name name;
    private final Description description;
    private final Date date;
    private final Calories calories;

    public EqualPropertiesPredicateForExercise(Name name, Description description, Date date, Calories calories) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.calories = calories;
    }

    @Override
    public boolean test(Exercise exercise) {
        boolean result = true;

        if (name != null) {
            result = name.equals(exercise.getName());
        }
        if (description != null) {
            result = result && (description.equals(exercise.getDescription()));
        }
        if (date != null) {
            result = result && (date.equals(exercise.getDate()));
        }
        if (calories != null) {
            result = result && (calories.equals(exercise.getCalories()));
        }

        return result;
    }

}

