package seedu.address.model.exercise;

import java.util.function.Predicate;

public class TheMostRecentDatePredicateForExercise implements Predicate<Exercise> {

    private final Name name;
    private final Date date;

    public TheMostRecentDatePredicateForExercise(Name name, Date date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public boolean test(Exercise exercise) {
        return name == exercise.getName() && date == exercise.getDate();
    }

}

