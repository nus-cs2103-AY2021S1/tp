package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.WordBank;
import seedu.address.model.view.View;

/**
 * Clears GreenTea.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Green Tea has been cleared! You may continue to add new decks";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setWordBank(new WordBank());
        model.clearEntryList();
        model.setCurrentView(View.START_VIEW);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
