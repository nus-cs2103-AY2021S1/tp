package seedu.address.logic.commands.deck;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;

/**
 * Removes a deck identified using it's displayed index from the word bank.
 */
public class RemoveDeckCommand extends Command {

    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Removes the deck identified by the index number used in the displayed deck list.\n"
        + "Parameters: INDEX (must be a positive integer that is less than 2,147,483,648)\n"
        + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_REMOVE_DECK_SUCCESS = "Removed Deck: %1$s. Current deck (if any) "
            + "has been unselected, please reselect a new deck";

    private final Index targetIndex;

    /**
     * Constructs a RemoveDeckCommand object
     *
     * @param targetIndex Index of the object to be removed
     */
    public RemoveDeckCommand(Index targetIndex) {
        assert (targetIndex.getZeroBased() >= 0);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Deck> lastShownList = model.getFilteredDeckList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        Deck deckToRemove = lastShownList.get(targetIndex.getZeroBased());
        model.removeDeck(deckToRemove);
        return new CommandResult(String.format(MESSAGE_REMOVE_DECK_SUCCESS, deckToRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RemoveDeckCommand // instanceof handles nulls
            && targetIndex.equals(((RemoveDeckCommand) other).targetIndex)); // state check
    }
}
