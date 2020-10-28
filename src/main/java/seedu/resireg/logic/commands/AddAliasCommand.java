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
public class AddAliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_SUCCESS = "New alias added: %1$s";
    public static final String MESSAGE_DUPLICATE_ALIAS = "This alias already exists in ResiReg";

    public static final Help HELP = new Help(COMMAND_WORD,
        "Adds an alias to ResiReg.\n",
        "Parameters: "
            + PREFIX_COMMAND + "COMMAND "
            + PREFIX_ALIAS + "ALIAS ");

    private final CommandWordAlias toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddAliasCommand(CommandWordAlias alias) {
        requireNonNull(alias);
        toAdd = alias;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasCommandWordAlias(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ALIAS);
        }

        model.addCommandWordAlias(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddAliasCommand // instanceof handles nulls
            && toAdd.equals(((AddAliasCommand) other).toAdd));
    }
}
