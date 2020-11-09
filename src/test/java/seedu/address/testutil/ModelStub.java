package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Person;

/**
 * A default model stub that have all of the methods failing.
 */
public abstract class ModelStub implements Model {
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModuleCode(ModuleCode moduleCode) {
        return false;
    }

    @Override
    public void addModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteModule(ModuleCode moduleCode) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void assignInstructor(Person instructor, ModuleCode moduleCode) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void unassignAllInstructors() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void unassignInstructor(Person instructor, ModuleCode moduleCode) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return null;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isEmptyPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isEmptyModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void clearContacts() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void clearMod() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean moduleCodeHasInstructor(ModuleCode moduleCode, Person instructor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void unassignInstructorFromAll(Person instructor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void switchModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getSemester() {
        throw new AssertionError("This method should not be called.");
    }
}
