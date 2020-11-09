package seedu.address.model.exercise;

import java.util.List;
import java.util.function.Predicate;

public class PropertiesMatchPredicate implements Predicate<Exercise> {
    private final Name name;
    private final Description description;
    private final Date date;
    private final Calories calories;
    private final List<String> keywords;

    /**
     * Creates a predicate to find exercises with matched properties.
     */
    public PropertiesMatchPredicate(Name name, Description description,
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
            result = name.fullName.equalsIgnoreCase(exercise.getName().fullName);
        }
        if (description != null) {
            result = result && (description.value.equalsIgnoreCase(exercise.getDescription().value));
        }
        if (date != null) {
            result = result && (date.equals(exercise.getDate()));
        }
        if (calories != null) {
            result = result && (calories.equals(exercise.getCalories()));
        }
        if (keywords != null) {
            result = result && (new NameContainsKeywordsPredicate(keywords).test(exercise)
                || new DescriptionContainsKeywordsPredicate(keywords).test(exercise));
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof PropertiesMatchPredicate) {
            PropertiesMatchPredicate predicate = (PropertiesMatchPredicate) other;
            return (name == predicate.name || name.equals(predicate.name)) // name is null or names are equal
                    && (description == predicate.description || description.equals(predicate.description))
                    && (date == predicate.date || date.equals(predicate.date))
                    && (calories == predicate.calories || calories.equals(predicate.calories))
                    && (keywords == predicate.keywords || keywords.equals(predicate.keywords));
        }
        return false;
    }

}

