package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Reverts the {@code model}'s address book to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String REDO_COMMAND_USAGE = COMMAND_WORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redo the recent command. "
            + "\nCommands that can be redone:"
            + "\n1. tag"
            + "\n2. retag"
            + "\n3. untag"
            + "\n4. label"
            + "\n5. unlabel"
            + "\n6. clear"
            + "\n7. undo"
            + "\n\nExample: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Redo success!";
    public static final String MESSAGE_FAILURE = "Unable to redo! No more commands to redo!";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoAddressBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoAddressBook();

        logger.info(MESSAGE_SUCCESS + "\n" + model.getAddressBook());

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
