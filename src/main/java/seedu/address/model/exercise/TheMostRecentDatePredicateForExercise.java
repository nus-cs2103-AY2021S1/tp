package seedu.address.model.exercise;

import java.util.function.Predicate;

public class TheMostRecentDatePredicateForExercise implements Predicate<Exercise> {

    private final Name name;
    private final Date date;

    /**
     * Creates a predicate to look for exercises with specified name and date.
     */
    public TheMostRecentDatePredicateForExercise(Name name, Date date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public boolean test(Exercise exercise) {
        if (name == null || date == null) {
            return false;
        }
        return name.fullName.equalsIgnoreCase(exercise.getName().fullName) && date.equals(exercise.getDate());
    }

}

