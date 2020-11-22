package seedu.forgetfulnus.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.forgetfulnus.commons.core.GuiSettings;
import seedu.forgetfulnus.logic.commands.CommandResult;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.logic.parser.exceptions.ParseZeroException;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.flashcard.FlashCard;

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
    CommandResult execute(String commandText) throws CommandException, ParseException, ParseZeroException;

    /**
     * Returns the Glossary.
     *
     * @see seedu.forgetfulnus.model.Model#getGlossary()
     */
    ReadOnlyGlossary getGlossary();

    /** Returns an unmodifiable view of the filtered list of flashcards */
    ObservableList<FlashCard> getFilteredFlashCardList();

    /**
     * Returns the user prefs' glossary file path.
     */
    Path getGlossaryFilePath();

    /**
     * Returns the user prefs' scores file path
     */

    Path getScoresFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
