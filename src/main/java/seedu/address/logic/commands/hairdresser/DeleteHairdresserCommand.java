package seedu.address.logic.commands.hairdresser;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DeleteEntityCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;

/**
 * Deletes a hairdresser identified using it's displayed index from docX.
 */
public class DeleteHairdresserCommand extends DeleteEntityCommand<HairdresserId> {

    public static final String COMMAND_WORD = "delete_hairdresser";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the hairdresser identified by the hairdresser ID used in the displayed hairdresser list.\n"
            + "Parameters: HAIRDRESSER_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_HAIRDRESSER_SUCCESS = "Deleted Hairdresser: %1$s";

    public DeleteHairdresserCommand(HairdresserId targetId) {
        super(targetId);
    }

    @Override
    public String deleteFromModel(Model model) throws CommandException {
        Hairdresser hairdresserToDelete = model.getHairdresserById(targetId);
        if (hairdresserToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_HAIRDRESSER_DISPLAYED_ID);
        }
        model.deleteHairdresser(hairdresserToDelete);
        return String.format(MESSAGE_DELETE_HAIRDRESSER_SUCCESS, hairdresserToDelete);
    }
}
