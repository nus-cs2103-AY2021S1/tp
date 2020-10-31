package seedu.cc.logic.commands.entrylevel;

import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;

import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;

/**
 * Undo the latest command executed in CommonCents.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Previous command undone!";
    public static final String MESSAGE_NO_PREVIOUS_COMMAND = "You did not input any command prior to this!";

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) throws CommandException {
        requireAllNonNull(model, activeAccount);

        if (activeAccount.hasNoPreviousState()) {
            throw new CommandException(MESSAGE_NO_PREVIOUS_COMMAND);
        }

        activeAccount.returnToPreviousState();

        model.setAccount(activeAccount.getAccount());
        return CommandResultFactory.createCommandResultForEntryListChangingCommand(MESSAGE_SUCCESS);
    }
}
