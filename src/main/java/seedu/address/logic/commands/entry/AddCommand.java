package seedu.address.logic.commands.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSLATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORD;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.view.View;

/**
 * Adds an entry to the word bank.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a entry to the word bank. "
            + "Parameters: "
            + PREFIX_WORD + "WORD "
            + PREFIX_TRANSLATION + "TRANSLATION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_WORD + "Fruits "
            + PREFIX_TRANSLATION + "果物";

    public static final String MESSAGE_SUCCESS = "New entry added: %1$s";

    /**
     * There cannot be duplicate entries in the word bank.
     * Two entries are considered to be duplicates if they have the same translation.
     * An entry cannot contain reserve command words like /play or /stop.
     */
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the word bank."
            + "Two entries cannot have the same translation";
    public static final String MESSAGES_FORBIDDEN = "Word or translations can't be %s ";

    private final Entry toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Entry}
     */
    public AddCommand(Entry entry) {
        requireNonNull(entry);
        toAdd = entry;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (toAdd.getWord().toString().equals("/stop") || toAdd.getTranslation().toString().equals("/stop")
                || toAdd.getWord().toString().equals("/play") || toAdd.getTranslation().toString().equals("/play")) {
            throw new CommandException(String.format(MESSAGES_FORBIDDEN, "\"/stop\" or \"/play\""));
        }

        if (model.getCurrentDeck() == null) {
            throw new CommandException(Messages.MESSAGE_NO_DECK_SELECTED);
        }
        if (model.hasEntry(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
        }

        model.addEntry(toAdd);
        model.replaceEntryList();
        model.setCurrentView(View.ENTRY_VIEW);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
