package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_COMMAND;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.storage.Storage;

/**
 * Adds a student to the address book.
 */
public class DeleteAliasCommand extends Command {

    public static final String COMMAND_WORD = "dealias";

    public static final String MESSAGE_SUCCESS = "The alias %1$s has been deleted.";
    public static final String MESSAGE_INVALID_ALIAS = "This alias doesn't exist.";

    public static final Help HELP = new Help(COMMAND_WORD,
        "Deletes an alias from ResiReg.\n",
        "Parameters: "
            + PREFIX_COMMAND + "COMMAND "
            + PREFIX_ALIAS + "ALIAS ");

    private final CommandWordAlias toDelete;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public DeleteAliasCommand(CommandWordAlias alias) {
        requireNonNull(alias);
        toDelete = alias;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.hasCommandWordAlias(toDelete)) {
            throw new CommandException(MESSAGE_INVALID_ALIAS);
        }

        model.deleteCommandWordAlias(toDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteAliasCommand // instanceof handles nulls
            && toDelete.equals(((DeleteAliasCommand) other).toDelete));
    }
}

