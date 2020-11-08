package seedu.address.logic.commands.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXERCISES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all exercises in fitNUS to the user.
 */
public class ExerciseListCommand extends Command {

    public static final String COMMAND_WORD = "exercise_list";

    public static final String MESSAGE_SUCCESS = "Listed all exercises";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ExerciseListCommand;
    }
}
