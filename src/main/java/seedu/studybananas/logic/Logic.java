package seedu.studybananas.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.studybananas.commons.core.GuiSettings;
import seedu.studybananas.commons.core.index.Index;
import seedu.studybananas.logic.commands.Command;
import seedu.studybananas.logic.commands.commandresults.CommandResult;
import seedu.studybananas.logic.commands.exceptions.CommandException;
import seedu.studybananas.logic.parser.exceptions.ParseException;
import seedu.studybananas.model.Model;
import seedu.studybananas.model.flashcard.Flashcard;
import seedu.studybananas.model.flashcard.FlashcardSet;
import seedu.studybananas.model.flashcard.FlashcardSetName;
import seedu.studybananas.model.quiz.Quiz;
import seedu.studybananas.model.systemlevelmodel.ReadOnlyFlashcardBank;
import seedu.studybananas.model.systemlevelmodel.ReadOnlySchedule;
import seedu.studybananas.model.task.Task;

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
     * Executes the command without parsing commandText.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult execute(Command command) throws CommandException;

    /**
     * Parse command without executing it.
     * @param commandText
     * @return the result of the command.
     * @throws ParseException If an error occurs during parsing.
     */
    Command parse(String commandText) throws ParseException;

    /**
     * Get {@Code FlashcardSet} by {@Code Index}.
     * @param idx index of the flashcardSet.
     */
    FlashcardSet getFlashcardSetFromIndex(Index idx);

    /**
     * Get {@Code FlashcardSet} by {@Code FlashcardSetName}.
     * @param flsetName name of the flashcardSet.
     */
    FlashcardSet getFlashcardSetFromName(FlashcardSetName flsetName);


    /**
     * Returns the Schedule.
     *
     * @see Model#getSchedule()
     */
    ReadOnlySchedule getSchedule();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getUpcomingTaskList();

    /**
     * Returns the user prefs' schedule file path.
     */
    Path getScheduleFilePath();



    /**
     * Returns the Flashcard Bank.
     *
     * @see Model#getFlashcardBank() ()
     */
    ReadOnlyFlashcardBank getFlashcardBank();

    /** Returns an unmodifiable view of the filtered list of flashcard sets */
    ObservableList<FlashcardSet> getFilteredFlashcardSetList();

    /**
     * Returns the user prefs' flashcard bank file path.
     */
    Path getFlashcardBankFilePath();

    /** Returns the selected flashcards to view */
    ObservableList<Flashcard> getFlashcardSetToView();

    /** Sets the flashcardset to the view observable list**/
    void setFlashcardSetToView(FlashcardSet flashcardSet);


    /**
     * Returns the quiz queried.
     */
    Quiz getQuizRecordsToView();


    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);




}
