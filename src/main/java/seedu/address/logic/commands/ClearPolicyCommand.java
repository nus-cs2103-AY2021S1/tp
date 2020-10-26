package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class ClearPolicyCommand extends Command {
    public static final String COMMAND_WORD = "clearp";
    public static final String MESSAGE_SUCCESS = "Policy list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearPolicyList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
