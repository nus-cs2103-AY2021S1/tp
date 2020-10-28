package seedu.address.model.explorer;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof FileList)) {
            return false;
        }
        FileList other = (FileList) obj;
        return this.internalList.equals(other.internalList)
                && this.internalUnmodifiableList.equals(other.internalUnmodifiableList);
    }
}
