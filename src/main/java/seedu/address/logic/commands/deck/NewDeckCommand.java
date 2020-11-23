package seedu.address.logic.commands.deck;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;

/**
 * A command class to add a new deck to the word bank
 */
public class NewDeckCommand extends Command {

    public static final String COMMAND_WORD = "new";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Create an empty deck of flashcards. "
            + "Parameters: "
            + "DECK_NAME "
            + "Example: " + COMMAND_WORD + " "
            + "Japanese ";

    public static final String MESSAGE_SUCCESS = "New deck added: %1$s";
    public static final String MESSAGE_DUPLICATE_DECK = "This deck already exists in the word bank";

    private final Deck toAdd;

    /**
     * Constructs a NewDeckCommand to add the given deck {@code deck}.
     * @param deck Deck to be added.
     */
    public NewDeckCommand(Deck deck) {
        requireNonNull(deck);
        toAdd = deck;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasDeck(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DECK);
        }

        model.addDeck(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NewDeckCommand // instanceof handles nulls
                && toAdd.equals(((NewDeckCommand) other).toAdd));
    }
}
