package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.flashcard.Flashcard;
import seedu.address.flashcard.Question;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the flashcard identified by the index number used in the displayed flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_OPEN_FLASHCARD_SUCCESS = "Opened Flashcard: %1$s";

    private final Index targetIndex;

    public OpenCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToOpen = lastShownList.get(targetIndex.getZeroBased());
        Question question = flashcardToOpen.getQuestion();
        return new CommandResult(String.format(MESSAGE_OPEN_FLASHCARD_SUCCESS, flashcardToOpen), question,
                null, true);
    }
}
