package seedu.address.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.TagNameContainsKeywordsPredicate;

/**
 * Finds and lists all tags in Projact whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class TagFindCommand extends Command {

    public static final String COMMAND_WORD = "tagfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tags whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2106 ma1101r";

    private final TagNameContainsKeywordsPredicate predicate;

    public TagFindCommand(TagNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTagList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TAGS_LISTED_OVERVIEW, model.getFilteredTagList().size()),
                false, false, false, false, true, false);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TagFindCommand)) {
            return false;
        }
        TagFindCommand other = (TagFindCommand) obj;
        return predicate.equals(other.predicate);
    }

}
