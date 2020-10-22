package seedu.address.model;

import java.io.File;

import javafx.collections.ObservableList;
import seedu.address.model.explorer.FileList;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the tag list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();

    FileList getFileList();

    /**
     * Returns an unmodifiable view of the file list.
     * This list will not contain any duplicate files.
     */
    ObservableList<File> getObservableFileList();

}
