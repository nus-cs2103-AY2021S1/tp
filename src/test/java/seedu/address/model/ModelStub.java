package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.model.notes.note.Note;
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
    public void setNotebook(ReadOnlyNotebook notebook) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyNotebook getNotebook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasNote(Note note) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteNote(Note target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addNote(Note note) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setNote(Note target, Note editedNote) {
        throw new AssertionError("This method should not be called.");
    }
}
