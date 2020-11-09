package seedu.address.logic.commands;

import seedu.address.model.Model;

public class HomeCommand extends Command {

    public static final String COMMAND_WORD = "home";

    public static final String HOME_SUCCESS_MESSAGE = "Showing home page.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HOME_SUCCESS_MESSAGE);
    }
}
