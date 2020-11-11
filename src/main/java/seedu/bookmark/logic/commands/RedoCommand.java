package seedu.bookmark.logic.commands;

import java.util.logging.Logger;

import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.exceptions.RedoException;

public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redoes the most recent undone command. \n"
            + "Usage: redo";

    public static final String MESSAGE_SUCCESS = "Previous undone command successfully redone";
    public static final String MESSAGE_NO_UNDONE_CHANGES = "No commands available to redo";
    private final Logger logger = LogsCenter.getLogger(getClass());


    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            String initialBookList = model.getFilteredBookList().toString();
            logger.info("Redo initiated, initial books: \n" + initialBookList);

            model.redo();

            String finalBookList = model.getFilteredBookList().toString();
            logger.info("Redo success, books modified: " + finalBookList);

            ViewType newViewType = VIEW_TYPE_MANAGER.getViewType(model.getCurrentState());
            VIEW_TYPE_MANAGER.setCurrentView(newViewType);
            return new CommandResult(MESSAGE_SUCCESS, false, false,
                    newViewType);
        } catch (RedoException e) {
            String unmodifiedBookList = model.getFilteredBookList().toString();
            logger.info("Redo failed, books not modified: " + unmodifiedBookList);
            throw new CommandException(MESSAGE_NO_UNDONE_CHANGES);
        }
    }
}
