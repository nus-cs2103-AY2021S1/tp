package seedu.jarvis.logic;

import java.nio.file.Path;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.jarvis.commons.core.GuiSettings;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.ReadOnlyAddressBook;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.model.mission.Mission;
import seedu.jarvis.model.quest.Quest;
import seedu.jarvis.model.student.Student;
import seedu.jarvis.model.task.Task;

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
     * Returns the AddressBook.
     *
     * @see seedu.jarvis.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered list of missions */
    ObservableList<Mission> getFilteredMissionList();

    /** Returns an unmodifiable view of the filtered list of quests */
    ObservableList<Quest> getFilteredQuestList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered list of consultations */
    ObservableList<Consultation> getFilteredConsultationList();

    /** Returns an unmodifiable view of the filtered list of mastery checks */
    ObservableList<MasteryCheck> getFilteredMasteryCheckList();

    /**
     * Returns the user prefs' jarvis book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns whether login.json has username specified.
     */
    boolean hasUsername();

    /**
     * Returns whether login.json has password specified.
     */
    boolean hasPassword();

    /**
     * Saves model to storage
     */
    void saveToStorage() throws CommandException;

    /**
     * Returns the greeting to be displayed.
     */
    StringProperty getGreeting();

    /**
     * Returns the string representation of a summary for all ungraded missions and quests, as well as upcoming
     * Consultations, Mastery Checks and remaining Tasks.
     */
    StringProperty getSummary();
}
