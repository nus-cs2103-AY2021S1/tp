package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts the flashcards in the flashcard list in order of priority.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the flashcard list.\n"
            + "Parameters: ASC / DESC (can be in lowercase)\n"
            + "Example: " + COMMAND_WORD + " ASC";

    private final String sortOrder;

    public SortCommand(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredFlashcardList(sortOrder);
        return new CommandResult("Flashcards sorted.");
    }
}
