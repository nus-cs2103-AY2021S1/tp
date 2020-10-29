package seedu.address.logic;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.event.ScheduleViewMode;
import seedu.address.model.student.Student;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setReeve(ReadOnlyReeve newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyReeve getReeve() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteStudent(Student target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Student> getSortedStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateSortedStudentList(Comparator<? super Student> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSchedulerWithEvents(ReadOnlyEvent events) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyEvent getSchedule() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getScheduleFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setScheduleFilePath(Path scheduleFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public LocalDateTime getScheduleViewDateTime() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setScheduleViewDateTime(LocalDateTime targetDateTime) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ScheduleViewMode getScheduleViewMode() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setScheduleViewMode(ScheduleViewMode viewMode) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addEvent(Event eventToAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEvent(Event eventToCheck) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<VEvent> getVEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isClashingEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

}
