package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.ExerciseModel.PREDICATE_SHOW_ALL_EXERCISE;

import seedu.address.model.ExerciseModel;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends CommandForExercise {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all exercises";


    @Override
    public CommandResult execute(ExerciseModel model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISE);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
