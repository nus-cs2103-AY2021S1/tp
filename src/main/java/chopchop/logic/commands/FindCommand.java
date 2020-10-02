package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.model.Model;
import chopchop.model.attributes.NameContainsKeywordsPredicate;

/**
 * Finds and lists all recipes in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    protected final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public abstract CommandResult execute(Model model);

    @Override
    public abstract boolean equals(Object other);
}
