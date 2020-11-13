package seedu.internhunter.logic.commands.view;

import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ITEM_NAME;

import seedu.internhunter.logic.commands.Command;
import seedu.internhunter.logic.commands.CommandResult;

/**
 * Views an Item in the Item list.
 */
public abstract class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views a "
            + COMPANY_NAME + ", "
            + APPLICATION_NAME + ", or "
            + PROFILE_ITEM_NAME
            + " in InternHunter.\n"
            + "Parameters: ITEM_TYPE INDEX\n"
            + "Note: Valid ITEM_TYPEs are "
            + "'" + COMPANY_ALIAS + "', "
            + "'" + APPLICATION_ALIAS + "' or "
            + "'" + PROFILE_ALIAS + "'. "
            + "INDEX must be a positive integer.\n"
            + "Example: "
            + COMMAND_WORD + " " + COMPANY_ALIAS + " 3\n";

    public static final String MESSAGE_ALREADY_VIEWING = "Already viewing %1$s %2$s";

    /**
     * Obtains the CommandResult to tell the user that the user is already viewing this current item.
     *
     * @return CommandResult that tells the user that it is already viewing the current item.
     */
    public CommandResult getAlreadyViewingCommandResult(String messageAlreadyViewing) {
        return new CommandResult(messageAlreadyViewing, false, false, false, false);
    }

}
