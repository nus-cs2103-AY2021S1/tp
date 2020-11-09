package tutorspet.logic.commands.moduleclass;

import static java.util.Objects.requireNonNull;
import static tutorspet.commons.core.Messages.MESSAGE_MODULE_CLASS_LISTED_OVERVIEW;

import tutorspet.logic.commands.Command;
import tutorspet.logic.commands.CommandResult;
import tutorspet.model.Model;
import tutorspet.model.components.name.NameContainsKeywordsPredicate;
import tutorspet.model.moduleclass.ModuleClass;

/**
 * Finds and lists all classes in the student manager whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindModuleClassCommand extends Command {

    public static final String COMMAND_WORD = "find-class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all classes whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameter: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2103t cs2100";

    private final NameContainsKeywordsPredicate<ModuleClass> predicate;

    public FindModuleClassCommand(NameContainsKeywordsPredicate<ModuleClass> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredModuleClassList(predicate);
        return new CommandResult(String.format(MESSAGE_MODULE_CLASS_LISTED_OVERVIEW,
                model.getFilteredModuleClassList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit it same object
                || (other instanceof FindModuleClassCommand // instanceof handles nulls
                && predicate.equals(((FindModuleClassCommand) other).predicate)); // state check
    }
}
