package seedu.address.logic.commands.contactlistcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Edits a specific contact in the contact list.
 */
public class EditContactCommand extends Command {
    public static final String COMMAND_WORD = "editcontact";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
