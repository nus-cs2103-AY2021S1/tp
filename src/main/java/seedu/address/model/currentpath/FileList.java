package seedu.address.model.currentpath;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The list of children files under the current path.
 */
public class FileList {

    private final ObservableList<File> internalList = FXCollections.observableArrayList();
    private final ObservableList<File> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public void setFiles(List<File> files) {
        requireAllNonNull(files);
        internalList.setAll(files);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<File> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }
}
