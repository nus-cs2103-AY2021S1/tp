package seedu.bagel.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_SET;
import static seedu.bagel.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import java.util.function.Predicate;

import seedu.bagel.model.Model;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all flashcards by default. Use list with set number as a parameter to list all flashcards "
            + "of that set\n"
            + "Parameters: s/SET_NUMBER (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SET + "1";

    public static final String MESSAGE_SUCCESS = "Listed all flashcards";

    public static final String MESSAGE_SUCCESS_SET = "Listed all flashcards in set";

    public static final String MESSAGE_EMPTY_LIST = "No flashcards in Bagel yet";

    public static final String MESSAGE_EMPTY_SET = "No flashcards in set";

    private final FlashcardSet flashcardSet;

    public ListCommand() {
        this.flashcardSet = null;
    }

    public ListCommand(FlashcardSet flashcardSet) {
        this.flashcardSet = flashcardSet;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (flashcardSet == null) {
            model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);

            if (model.getFilteredFlashcardList().size() == 0) {
                return new CommandResult(MESSAGE_EMPTY_LIST);
            }

            return new CommandResult(MESSAGE_SUCCESS);
        }

        Predicate<Flashcard> predicateShowFlashcardsInSet = flashcard ->
                flashcard.getFlashcardSet().equals(flashcardSet);
        model.updateFilteredFlashcardList(predicateShowFlashcardsInSet);

        if (model.getFilteredFlashcardList().size() == 0) {
            return new CommandResult(MESSAGE_EMPTY_SET + " " + flashcardSet.value);
        }

        return new CommandResult(MESSAGE_SUCCESS_SET + " " + flashcardSet.value);
    }
}
