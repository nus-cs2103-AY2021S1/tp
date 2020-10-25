package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.ExerciseModel;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.EqualPropertiesPredicateForExercise;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Name;
import seedu.address.model.exercise.NameContainsKeywordsPredicateForExercise;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends CommandForExercise {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all exercises whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "push up";

    /*private final Name name;
    private final Description description;
    private final Date date;
    private final Calories calories;
    private final String keyword;

    public FindCommand(Name name, Description description, Date date, Calories calories, String keyword) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.calories = calories;
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(ExerciseModel model) {
        //model.updateFilteredExerciseList();
        return new CommandResult(String.format(Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW, 1));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && name.equals(((FindCommand) other).name) // state check
                && description.equals(((FindCommand) other).description)
                && date.equals(((FindCommand) other).date)
                && calories.equals(((FindCommand) other).calories)
                && keyword.equals(((FindCommand) other).keyword));
    }*/

    private final EqualPropertiesPredicateForExercise predicate;

    public FindCommand(EqualPropertiesPredicateForExercise predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(ExerciseModel model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW, model.getFilteredExerciseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
