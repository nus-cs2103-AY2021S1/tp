package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.expense.commons.core.LogsCenter;
import seedu.expense.logic.LogicManager;
import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.Model;
import seedu.expense.model.tag.Tag;

/**
 * Switches a category expense in the expense book.
 */
public class SwitchCommand extends Command {

    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches a category expense in the expense book. "
        + "Parameters: "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_TAG + "Food ";

    public static final String MESSAGE_INVALID_CATEGORY = "No such category account:  %1$s. ";
    public static final String MESSAGE_SUCCESS = "Category expense switched:  %1$s. ";

    private final Tag toMatch;

    /**
     * Creates an SwitchCommand to switch to the specified {@code Tag} category
     */
    public SwitchCommand(Tag tag) {
        requireNonNull(tag);
        toMatch = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasCategory(toMatch)) {
            throw new CommandException(String.format(MESSAGE_INVALID_CATEGORY, toMatch));
        }

        LogsCenter.getLogger(LogicManager.class).info(
            "----------------[USER COMMAND][" + COMMAND_WORD + " " + toMatch + "]");

        model.switchCategory(toMatch);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toMatch));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SwitchCommand // instanceof handles nulls
            && toMatch.equals(((SwitchCommand) other).toMatch));
    }
}
