package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.tag.TagNameContainsKeywordsPredicate;

/**
 * Finds and lists all tag in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String FIND_COMMAND_USAGE = COMMAND_WORD + " " + "KEYWORD";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tag whose tag name/label contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n\n"
            + "Parameters: KEYWORD1 [KEYWORD2]...\n\n"
            + "Example: \n\t(i) (Find by tag name) " + COMMAND_WORD + " tagname1 tagname2 tagname3"
            + "\n\t(ii) (Find by label) " + COMMAND_WORD + " label1 label2 label3"
            + "\n\t(iii) (Find by label & tag name) " + COMMAND_WORD + " tagname1 label1";

    private final TagNameContainsKeywordsPredicate predicate;

    public FindCommand(TagNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTagList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TAGS_LISTED_OVERVIEW, model.getFilteredTagList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
