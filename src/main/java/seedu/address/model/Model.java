package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.event.ScheduleViewMode;
import seedu.address.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setReeve(ReadOnlyReeve reeve);

    /** Returns the AddressBook */
    ReadOnlyReeve getReeve();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Student student);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Student target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Student student);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Student> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Student> predicate);

    // =========== schedule ================================================================================
    void setSchedulerWithEvents(ReadOnlyEvent events);

    ReadOnlyEvent getSchedule();

    ReadOnlyVEvent getVEvents();

    Path getScheduleFilePath();

    void setScheduleFilePath(Path scheduleFilePath);

    LocalDateTime getScheduleViewDateTime();

    void setScheduleViewDateTime(LocalDateTime targetDateTime);

    ScheduleViewMode getScheduleViewMode();

    void setScheduleViewMode(ScheduleViewMode viewMode);

    void addVEvent(VEvent vEvent);

    boolean hasVEvent(VEvent vEvent);

    void removeVEvent(Index index);

    VEvent getVEvent(Index index);

    ObservableList<VEvent> getVEventList();
}
