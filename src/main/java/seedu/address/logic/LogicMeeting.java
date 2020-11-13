package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.meeting.meeting.Meeting;

/**
 * API of logic component for meeting
 */
public interface LogicMeeting {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the MeetingBook.
     *
     * @see ModelMeeting#getMeetingBook()
     */
    ReadOnlyMeetingBook getMeetingBook();

    /**
     * Returns the meeting that is currently in view.
     */
    Meeting getMeetingInView();

    /** Returns an unmodifiable view of the filtered list of Meetings */
    ObservableList<Meeting> getFilteredMeetingList();

    /** Returns an internal list of Meetings */
    ObservableList<Meeting> getInternalMeetingList();

    /**
     * Returns the user prefs' meeting book file path.
     */
    Path getMeetingBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

}
