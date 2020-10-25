package seedu.address.model.exercise;

import java.util.Arrays;
import java.util.function.Predicate;

public class PropertiesMatchPredicateForExercise implements Predicate<Exercise> {
    private final Name name;
    private final Description description;
    private final Date date;
    private final Calories calories;
    private final String[] keywords;

    public PropertiesMatchPredicateForExercise(Name name, Description description,
                                               Date date, Calories calories, String[] keywords) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.calories = calories;
        this.keywords = keywords;
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
        if (keywords != null) {
            result = result && new NameContainsKeywordsPredicateForExercise(Arrays.asList(keywords)).test(exercise);
        }

        return result;
    }

}

