package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.bookmark.logic.ViewType;
import seedu.bookmark.model.Library;
import seedu.bookmark.model.Model;

/**
 * Deletes all books stored.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All books have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLibrary(new Library());
        model.save();
        storeViewType(model.getCurrentState(), ViewType.DEFAULT);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
