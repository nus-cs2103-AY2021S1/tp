package seedu.resireg.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.logic.commands.CommandResult;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.logic.parser.exceptions.ParseException;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;

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
     * Returns the ResiReg.
     *
     * @see seedu.resireg.model.Model#getResiReg()
     */
    ReadOnlyResiReg getResiReg();

    /** Returns the current semester ResiReg is working with */
    Semester getSemester();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered list of rooms */
    ObservableList<Room> getFilteredRoomList();

    /** Returns an unmodifiable view of the filtered list of allocations */
    ObservableList<Allocation> getFilteredAllocationList();

    /** Returns an unmodifiable view of the list of bin items */
    ObservableList<BinItem> getFilteredBinItemList();

    /**
     * Returns an unmodifiable view of the list of commands entered by the user.
     * The list is ordered in chronological order.
     */
    ObservableList<String> getHistory();

    /**
     * Returns the user prefs' ResiReg file path.
     */
    Path getResiRegFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
