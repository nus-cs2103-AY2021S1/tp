package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.Model;
import seedu.expense.model.alias.AliasEntry;
import seedu.expense.model.alias.AliasMap;

/**
 * Edits the alias of an existing commands in the alias map.
 */
public class AliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gives a customised alias to a command.\n"
            + "Alias must not be the same as the default command words and the 'alias' nor 'clear alias' "
            + "command cannot have an alias.\n"
            + "Parameters: "
            + "[desired_alias] "
            + "[current_alias/if none, default_command]\n"
            + "Example: " + COMMAND_WORD + " find " + "get \n"
            + "To remove customised alias for a command, simply enter: "
            + COMMAND_WORD + " [default_command] [current_alias]";

    public static final String MESSAGE_EDIT_ALIAS_SUCCESS = "Edited alias: [%s] becomes [%s] ";
    public static final String MESSAGE_REMOVED_ALIAS_SUCCESS = "Removed alias. [%s] is no longer alias for [%s]. ";

    private final String previousAlias;
    private final String newAlias;

    /**
     * @param previousAlias   of a command, or default keyword
     * @param newAlias      to set to a command
     */
    public AliasCommand(String previousAlias, String newAlias) {
        requireNonNull(previousAlias);
        requireNonNull(newAlias);

        this.previousAlias = previousAlias;
        this.newAlias = newAlias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            String actualCommand = model.getAliasMap().getValue(previousAlias);
            model.setAlias(new AliasEntry(previousAlias, actualCommand),
                    new AliasEntry(newAlias, actualCommand));
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }
        if (AliasMap.RESERVED_KEYWORDS.contains(newAlias)) {
            return new CommandResult(String.format(MESSAGE_REMOVED_ALIAS_SUCCESS, previousAlias, newAlias));
        }
        return new CommandResult(String.format(MESSAGE_EDIT_ALIAS_SUCCESS, previousAlias, newAlias));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AliasCommand)) {
            return false;
        }

        // state check
        AliasCommand e = (AliasCommand) other;
        return previousAlias.equals(e.previousAlias)
                && newAlias.equals(e.newAlias);
    }

}
