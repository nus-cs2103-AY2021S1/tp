package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.event.SchedulePrefs;
import seedu.address.model.event.ScheduleViewMode;
import seedu.address.model.event.Scheduler;
import seedu.address.model.student.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Reeve reeve;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final Scheduler scheduler;
    private final SchedulePrefs schedulePrefs;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyReeve addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyEvent schedule) {
        super();
        requireAllNonNull(addressBook, userPrefs, schedule);

        logger.fine("Initializing with address book: " + addressBook
                + ", schedule : " + schedule + " and user prefs " + userPrefs);

        this.reeve = new Reeve(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.reeve.getStudentList());
        this.scheduler = new Scheduler(schedule);
        this.schedulePrefs = new SchedulePrefs(ScheduleViewMode.WEEKLY, LocalDateTime.now());
    }

    public ModelManager() {
        this(new Reeve(), new UserPrefs(), new Scheduler());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getScheduleFilePath() {
        return userPrefs.getScheduleFilePath();
    }

    @Override
    public void setScheduleFilePath(Path scheduleFilePath) {
        requireNonNull(scheduleFilePath);
        userPrefs.setScheduleFilePath(scheduleFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setReeve(ReadOnlyReeve reeve) {
        this.reeve.resetData(reeve);
    }

    @Override
    public ReadOnlyReeve getReeve() {
        return reeve;
    }

    @Override
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return reeve.hasStudent(student);
    }

    @Override
    public void deletePerson(Student target) {
        reeve.removeStudent(target);
    }

    @Override
    public void addPerson(Student student) {
        reeve.addStudent(student);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        reeve.setStudent(target, editedStudent);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredPersonList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    //=========== schedule ================================================================================
    @Override
    public void setSchedulerWithEvents(ReadOnlyEvent events) {
        this.scheduler.resetData(events);
    }

    @Override
    public ReadOnlyEvent getSchedule() {
        return scheduler;
    }

    @Override
    public LocalDateTime getScheduleViewDateTime() {
        return schedulePrefs.getViewDateTime();
    }

    @Override
    public void setScheduleViewDateTime(LocalDateTime viewDateTime) {
        schedulePrefs.setViewDateTime(viewDateTime);
    }

    @Override
    public ScheduleViewMode getScheduleViewMode() {
        return schedulePrefs.getViewMode();
    }

    @Override
    public void setScheduleViewMode(ScheduleViewMode viewMode) {
        schedulePrefs.setViewMode(viewMode);
    }

    @Override
    public void addEvent(Event eventToAdd) {
        scheduler.addEvent(eventToAdd);
    }

    @Override
    public boolean hasEvent(Event eventToCheck) {
        return scheduler.hasEvent(eventToCheck);
    }

    @Override
    public void removeEvent(Event toRemove) {
        scheduler.removeEvent(toRemove);
    }

    @Override
    public ObservableList<VEvent> getVEventList() {
        return scheduler.getVEvents();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return reeve.equals(other.reeve)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

    @Override
    public boolean isClashingEvent(Event event) {
        return scheduler.isClashingEvents(event);
    }


}
