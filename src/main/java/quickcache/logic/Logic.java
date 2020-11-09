package quickcache.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import quickcache.commons.core.GuiSettings;
import quickcache.logic.commands.CommandResult;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.logic.parser.exceptions.ParseException;
import quickcache.model.Model;
import quickcache.model.ReadOnlyQuickCache;
import quickcache.model.flashcard.Flashcard;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the QuickCache.
     *
     * @see Model#getQuickCache()
     */
    ReadOnlyQuickCache getQuickCache();

    /**
     * Returns an unmodifiable view of the filtered list of flashcards
     */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Returns the user prefs' quick cache file path.
     */
    Path getQuickCacheFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
