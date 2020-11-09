package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.NameContainsKeywordsPredicate;



/**
 * Encapsulates methods and information to finds and lists all modules in module list whose name contains any of
 * the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindModuleCommand extends Command {

    public static final String COMMAND_WORD = "findmodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all modules whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "CS2101 CS2102 CS2103";

    private final Predicate<Module> predicate;

    /**
     * Creates and initialises a new FindModuleCommand object.
     *
     * @param predicate Predicate object used to update the filtered displayed module list.
     */
    public FindModuleCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        model.commitModuleList();
        return new CommandResult(
                String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindModuleCommand // instanceof handles nulls
                && predicate.equals(((FindModuleCommand) other).predicate)); // state check
    }

}
