package seedu.address.model.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PropertiesMatchPredicateForExercise implements Predicate<Exercise> {
    private final Name name;
    private final Description description;
    private final Date date;
    private final Calories calories;
    private final List<String> keywords;

    public PropertiesMatchPredicateForExercise(Name name, Description description,
                                               Date date, Calories calories, List<String> keywords) {
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
            result = result && new NameContainsKeywordsPredicateForExercise(keywords).test(exercise);
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertiesMatchPredicateForExercise // instanceof handles nulls
                && name.equals(((PropertiesMatchPredicateForExercise) other).name)
                && description.equals(((PropertiesMatchPredicateForExercise) other).description)
                && date.equals(((PropertiesMatchPredicateForExercise) other).date)
                && calories.equals(((PropertiesMatchPredicateForExercise) other).calories)
                && keywords.equals(((PropertiesMatchPredicateForExercise) other).keywords)); // state check
    }

}

