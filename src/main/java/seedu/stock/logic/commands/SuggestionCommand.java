package seedu.stock.logic.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;

public class SuggestionCommand extends Command {

    private static final Logger logger = LogsCenter.getLogger(SuggestionCommand.class);

    private String toBeDisplayed;

    /**
     * Constructs a new suggestion command.
     *
     * @param toBeDisplayed The suggestion to be displayed to the user.
     */
    public SuggestionCommand(String toBeDisplayed) {
        this.toBeDisplayed = toBeDisplayed;
    }

    /**
     * Executes current suggestion command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A command result to be passed to the user.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.log(Level.INFO, "Starting to execute suggestion command");
        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_STOCKS);
        logger.log(Level.INFO, "Finished suggesting successfully");
        throw new CommandException(toBeDisplayed);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SuggestionCommand)) {
            return false;
        }

        // state check
        SuggestionCommand castedOther = (SuggestionCommand) other;
        return toBeDisplayed.equals(castedOther.toBeDisplayed);
    }

    /**
     * Returns the suggestion to be displayed to the user.
     *
     * @return The suggestion to be displayed to the user.
     */
    public String getToBeDisplayed() {
        return toBeDisplayed;
    }
}
