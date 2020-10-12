package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelForExercise;

/**
 * Edits the details of an existing exercise in the address book.
 */
public class UpdateExerciseCommand extends CommandForExercise {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the exercise "
            + "by the index number used in the displayed exercise list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "EXERCISE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_CALORIES + "CALORIES]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Push up"
            + PREFIX_DESCRIPTION + "30"
            + PREFIX_DATE + "09-07-2020"
            + PREFIX_CALORIES + "260";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Update command not implemented yet";

    @Override
    public CommandResult execute(ModelForExercise model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}