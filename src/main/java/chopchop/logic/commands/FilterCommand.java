package chopchop.logic.commands;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

/**
 * Finds and lists all ingredients in ingredient book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public abstract class FilterCommand extends Command {

    @Override
    public abstract CommandResult execute(Model model, HistoryManager historyManager);

    @Override
    public abstract boolean equals(Object other);

}
