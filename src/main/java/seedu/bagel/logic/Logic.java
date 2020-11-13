package seedu.bagel.logic;

import java.nio.file.Path;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.bagel.commons.core.GuiSettings;
import seedu.bagel.logic.commands.CommandResult;
import seedu.bagel.logic.commands.exceptions.CommandException;
import seedu.bagel.logic.parser.exceptions.ParseException;
import seedu.bagel.model.ReadOnlyBagel;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;

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
     * Returns the Bagel.
     *
     * @see seedu.bagel.model.Model#getBagel()
     */
    ReadOnlyBagel getBagel();

    /** Returns an unmodifiable view of the filtered list of flashcard */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Returns the user prefs' bagel file path.
     */
    Path getBagelFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns true if there is at least 1 existing set. Else returns false.
     *
     * @return true if there is at least 1 existing set.
     */
    boolean hasSet();

    Set<FlashcardSet> getSets();
}
