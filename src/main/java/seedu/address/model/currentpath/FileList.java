package seedu.address.model.currentpath;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class FileList {

    private final ObservableList<File> internalList = FXCollections.observableArrayList();
    private final ObservableList<File> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public FileList(List<File> files) {
        setFiles(files);
    }

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
