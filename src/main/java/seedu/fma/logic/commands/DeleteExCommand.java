package seedu.fma.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.fma.commons.core.LogsCenter;
import seedu.fma.commons.core.Messages;
import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.exceptions.CommandException;
import seedu.fma.model.Model;
import seedu.fma.model.exercise.Exercise;

/**
 * Deletes an exercise identified using it's displayed index from the exercise book.
 */
public class DeleteExCommand extends Command {

    public static final String COMMAND_WORD = "deleteex";

    public static final String AC_SUGGESTION = COMMAND_WORD + " <index>";

    public static final String MESSAGE_DELETE_EXERCISE_SUCCESS = "Deleted Exercise: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the exercise identified by the index number used in the displayed exercise list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private static final Logger logger = LogsCenter.getLogger(DeleteExCommand.class);

    private final Index targetIndex;

    public DeleteExCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing DeleteExCommand.");
        requireNonNull(model);
        List<Exercise> lastShownList = model.getFilteredExerciseList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
        }

        Exercise exerciseToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteExercise(exerciseToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EXERCISE_SUCCESS, exerciseToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteExCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteExCommand) other).targetIndex)); // state check
    }

}
