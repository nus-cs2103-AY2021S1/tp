package seedu.address.model.exercise;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class NameContainsKeywordsPredicateForExercise implements Predicate<Exercise> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicateForExercise(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Exercise exercise) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(exercise.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicateForExercise // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicateForExercise) other).keywords)); // state check
    }
}
