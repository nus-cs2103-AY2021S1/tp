package seedu.address.logic.commands.flashcardcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class ListFlashcardCommand extends Command {

    public static final String COMMAND_WORD = "list fl";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists flashcards in a flashcard set."
            + "Parameters: "
            + "<flashcardsetindex> "
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Listed all flashcard in selected flashcard set";

    private final Index flashcardSetIndex;

    public ListFlashcardCommand(Index flashcardSetIndex) {
        this.flashcardSetIndex = flashcardSetIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardSetList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
