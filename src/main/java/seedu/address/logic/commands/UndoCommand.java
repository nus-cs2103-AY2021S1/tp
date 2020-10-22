package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;




public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Previous command undone!";
    public static final String MESSAGE_NO_PREVIOUS_COMMAND = "You did not input any command prior to this!";

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);
        // TODO:
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
