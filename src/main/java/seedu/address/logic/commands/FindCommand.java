package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.vendor.NameContainsKeywordsPredicate;
import seedu.address.storage.Storage;

/**
 * Finds and lists all vendors in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and lists all food items containing "
            + "any of the specified keywords in their name\n"
            + "Format: find KEYWORD [MORE_KEYWORDS]\n"
            + "- KEYWORD are NOT case-sensitive\n"
            + "- KEYWORD filters tags as well.\n"
            + "Examples:\n"
            + "find milo: lists all food items containing the word 'milo' in their name.\n"
            + "find milo dinosaur: lists all food items containing the word 'milo' or 'dinosaur' in their name.";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);

        if (!model.isSelected()) {
            throw new CommandException(Messages.MESSAGE_VENDOR_NOT_SELECTED);
        }

        model.updateFilteredMenuItemList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FOOD_LISTED_OVERVIEW,
                        model.getFilteredMenuItemListSize()), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
