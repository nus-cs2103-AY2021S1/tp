package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.Model;
import seedu.address.model.person.ContactMatchesFindKeywordPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the name keywords
 * and the tag matches the given tag keyword.
 * Keyword matching is case insensitive.
 */
public class FindContactCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " contact"
        + ": Finds all persons whose names contain any of "
        + "the specified name keywords and the tag matches the given tag keyword (case-insensitive) "
        + "and displays them as a list with index numbers.\n"
        + "Parameters: [" + PREFIX_NAME + "NAME_KEYWORD [MORE_NAME_KEYWORDS]...] "
        + "[" + PREFIX_TAG + "TAG_KEYWORD]\n"
        + "Remarks: at least one of " + PREFIX_NAME + " or " + PREFIX_TAG + " must be included in the command. "
        + "Keyword cannot be empty\n"
        + "Example: " + COMMAND_WORD + " contact " + PREFIX_NAME + "alice bob charlie " + PREFIX_TAG + "friends";

    private final ContactMatchesFindKeywordPredicate predicate;

    public FindContactCommand(ContactMatchesFindKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
            "CONTACT");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCommand // instanceof handles nulls
                && predicate.equals(((FindContactCommand) other).predicate)); // state check
    }
}
