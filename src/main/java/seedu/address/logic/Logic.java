package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.MultipleAttributesException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyPlanus;
import seedu.address.model.StatisticsData;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;

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
    CommandResult execute(String commandText) throws CommandException, ParseException, MultipleAttributesException;

    /**
     * Returns the Planus.
     *
     * @see seedu.address.model.Model#getPlanus()
     */
    ReadOnlyPlanus getPlanus();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered list of lessons */
    ObservableList<Lesson> getFilteredLessonList();

    /** Returns an unmodifiable view of the filtered list of calendar tasks */
    ObservableList<Task> getFilteredCalendarList();

    /** Returns an unmodifiable view of the filtered list of calendar tasks */
    StatisticsData getStatisticsData();

    /**
     * Returns the user prefs' PlaNus file path.
     */
    Path getPlanusFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
