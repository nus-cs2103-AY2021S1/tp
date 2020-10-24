package seedu.address.logic.commands;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.explorer.CurrentPath;
import seedu.address.model.tag.Tag;

/**
 * A default model stub that have all of the methods failing.
 */
class ModelStub implements Model {
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
    public void addTag(Tag tag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTag(Tag tag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTag(Tag target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Tag> getFilteredTagList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public CurrentPath getCurrentPath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<File> getFilteredFileList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public List<Tag> findFilteredTagList(Predicate<Tag> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean canUndoAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean canRedoAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void undoAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void redoAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void commitAddressBook() {
        throw new AssertionError("This method should not be called.");
    }
}
