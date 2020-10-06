package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.item.NameIsExactlyPredicate;

/**
 * Finds the first item that matches what is searched for, and displays it's details.
 * Keyword matching is case insensitive.
 */
public class ViewDetailsCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the first item that matches "
            + "what is searched for, and displays it's details.\n"
            + "Parameters: KEYWORD \n"
            + "Example: " + COMMAND_WORD + "Bob's bone";

    private final NameIsExactlyPredicate predicate;

    public ViewDetailsCommand(NameIsExactlyPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(predicate);
        return new CommandResult("Displaying searched item", false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewDetailsCommand // instanceof handles nulls
                && predicate.equals(((ViewDetailsCommand) other).predicate)); // state check
    }
}
