package seedu.address.logic.commands.exercise;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.exercise.ExerciseNameContainsKeywordsPredicate;

/**
 * Finds and lists all exercises in fitNUS whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ExerciseFindCommand extends Command {

    public static final String COMMAND_WORD = "exercise_find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all exercises whose names contain ALL of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " bench press morning";

    private final ExerciseNameContainsKeywordsPredicate predicate;

    /**
     * Creates an ExerciseFindCommand to find the specified {@code ExerciseNameContainsKeywordsPredicate}
     */
    public ExerciseFindCommand(ExerciseNameContainsKeywordsPredicate predicate) {
        assert(predicate != null);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW, model.getFilteredExerciseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseFindCommand // instanceof handles nulls
                && predicate.equals(((ExerciseFindCommand) other).predicate)); // state check
    }
}
