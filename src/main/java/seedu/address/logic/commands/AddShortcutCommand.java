package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_COMMAND;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commandshortcut.CommandShortcutSet;
import seedu.address.logic.commandshortcut.exceptions.CommandWordException;
import seedu.address.model.Model;


public class AddShortcutCommand extends AddCommand {

    public static final String COMMAND_WORD = "add_shortcut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an shortcut to the address book. "
            + "Parameters: "
            + PREFIX_OLD_COMMAND + " ORIGINAL COMMAND"
            + PREFIX_NEW_COMMAND + " NEW COMMAND\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_OLD_COMMAND + " add_hairdresser "
            + PREFIX_NEW_COMMAND + " a_h ";

    public static final String MESSAGE_SUCCESS = "Success! Added %1$s as shortcut for %2$s";

    private String defaultWord;
    private String newShortcut;

    /**
     * Creates an add alias command given a default word and the new alias
     * @param defaultWord the original command
     * @param newShortcut the new command
     */
    public AddShortcutCommand(String defaultWord, String newShortcut) {
        this.defaultWord = defaultWord;
        this.newShortcut = newShortcut;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            CommandShortcutSet.getInstance().insertNewShortcut(defaultWord, newShortcut);
        } catch (CommandWordException ex) {
            throw new CommandException(ex.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, newShortcut, defaultWord));
    }
}
