package seedu.flashcard.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.flashcard.commons.core.GuiSettings;
import seedu.flashcard.logic.commands.CommandResult;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.logic.parser.exceptions.ParseException;
import seedu.flashcard.model.ReadOnlyFlashcardDeck;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the FlashcardDeck.
     *
     * @see seedu.flashcard.model.Model#getFlashcardDeck()
     */
    ReadOnlyFlashcardDeck getFlashcardDeck();

    /** Returns an unmodifiable view of the filtered list of flashcards */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Returns the user prefs' flashcard deck file path.
     */
    Path getFlashcardDeckFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
