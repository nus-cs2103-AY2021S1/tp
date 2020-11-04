package seedu.pivot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.model.Model;
import seedu.pivot.model.Pivot;

/**
 * Clears PIVOT.
 */
public class ClearCommand extends Command implements Undoable {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_CLEAR_SUCCESS = "PIVOT has been cleared!";

    private static final Page pageType = Page.MAIN;
    private static final Logger logger = LogsCenter.getLogger(ClearCommand.class);

    @Override
    public CommandResult execute(Model model) {
        logger.info("Clearing all cases in PIVOT...");
        requireNonNull(model);
        model.setPivot(new Pivot());
        model.commitPivot(MESSAGE_CLEAR_SUCCESS, this);
        return new CommandResult(MESSAGE_CLEAR_SUCCESS);
    }

    @Override
    public Page getPage() {
        return pageType;
    }
}
