package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import seedu.address.model.ExerciseBook;
import seedu.address.model.ExerciseModel;

/**
 * Clears the address book.
 */
public class ClearCommand extends CommandForExercise {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Exercise book has been cleared!";


    @Override
    public CommandResult execute(ExerciseModel model) throws IOException {
        requireNonNull(model);
        model.setExerciseBook(new ExerciseBook());
        model.resetTemplate();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
