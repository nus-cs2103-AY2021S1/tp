package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.Tr4cker;

/**
 * Resets TR4CKER.
 */
public class ResetCommand extends Command {

    public static final String COMMAND_WORD = "reset";

    public static final String MESSAGE_SUCCESS = "TR4CKER has been reset!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTr4cker(new Tr4cker());

        CommandResult commandResult = new CommandResult(MESSAGE_SUCCESS);
        commandResult.setHomeTab();
        return commandResult;
    }

}
