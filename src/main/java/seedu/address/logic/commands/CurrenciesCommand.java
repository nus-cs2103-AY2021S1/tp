package seedu.address.logic.commands;

import seedu.address.model.Model;

public class CurrenciesCommand extends Command {
    public static final String COMMAND_WORD = "show-codes";
    public static final String SUCCESSFUL_MESSAGE = "A full list of supported currencies "
            + "with their currency codes are shown in the table!";

    /**
     * Executes the help command.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in the help message to be shown to the user.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SUCCESSFUL_MESSAGE,
                false, false, false, false, true, false);
    }

}
