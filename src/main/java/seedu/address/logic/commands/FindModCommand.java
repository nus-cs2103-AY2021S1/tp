package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.CodeOrNameMatchesKeywordPredicate;

public class FindModCommand extends Command {

    public static final String COMMAND_WORD = "findmod";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the module whose module code or name contains "
            + "the specified keywords (case-insensitive) and displays them as a list.\n"
            + "Parameters: " + "[MORE_KEYWORDS]... \n"
            + "Example: " + COMMAND_WORD + " cs2103";
    private final CodeOrNameMatchesKeywordPredicate predicate;
    public FindModCommand(CodeOrNameMatchesKeywordPredicate predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(this.predicate);
        return new CommandResult(String.format(Messages.MESSAGE_MODULE_LISTED, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindModCommand // instanceof handles nulls
                && predicate.equals(((FindModCommand) other).predicate)); // state check
    }
}
