package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;

import quickcache.model.Model;
import quickcache.model.QuickCache;

/**
 * Clears the QuickCache.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "QuickCache has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setQuickCache(new QuickCache());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
