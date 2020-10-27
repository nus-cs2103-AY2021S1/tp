package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;

import seedu.address.model.Model;

/**
 * Lists all tags in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "ls";
    public static final String LIST_COMMAND_USAGE = COMMAND_WORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the list of all tag. "
            + "\n\nExample: " + COMMAND_WORD;
    public static final String VALID_USER_INPUT = "";
    public static final String MESSAGE_SUCCESS = "Listed all tags";
    public static final String MESSAGE_INVALID_USER_INPUT = "Please don't pass in any argument for list command!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof ListCommand;
    }
}
