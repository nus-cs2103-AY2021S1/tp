package seedu.bookmark.logic.commands;

import java.util.logging.Logger;

import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.exceptions.UndoException;


public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the most recent command. \n"
            + "Only commands which modified, added or deleted the Books stored in the Library are considered \n"
            + "Usage: undo";

    public static final String MESSAGE_SUCCESS = "Previous command successfully undone";
    public static final String MESSAGE_MOST_RECENT_CHANGE = "No commands available to undo";
    private final Logger logger = LogsCenter.getLogger(getClass());

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            String initialBookList = model.getFilteredBookList().toString();
            logger.info("Undo initiated, initial books: \n" + initialBookList);

            model.undo();

            String finalBookList = model.getFilteredBookList().toString();
            logger.info("Undo success, books modified: " + finalBookList);

            ViewType newViewType = VIEW_TYPE_MANAGER.getViewType(model.getCurrentState());
            VIEW_TYPE_MANAGER.setCurrentView(newViewType);
            return new CommandResult(MESSAGE_SUCCESS, false, false,
                    newViewType);
        } catch (UndoException e) {
            String unmodifiedBookList = model.getFilteredBookList().toString();
            logger.info("Undo failed, books not modified: " + unmodifiedBookList);
            throw new CommandException(MESSAGE_MOST_RECENT_CHANGE);
        }
    }
}
