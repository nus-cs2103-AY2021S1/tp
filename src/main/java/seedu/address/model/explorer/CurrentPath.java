package seedu.address.model.explorer;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.tag.FileAddress;

/**
 * CurrentPath contains the current path (a FileAddress) of the HelloFile
 * file explorer. It also contains the list of children files under the path.
 */
public class CurrentPath {

    private FileAddress address;
    private FileList childrenFiles;

    /**
     * Constructs a CurrentPath.
     * @param fileList the list to be the children file list
     */
    public CurrentPath(String filePath, FileList fileList) {
        requireNonNull(fileList);
        address = new FileAddress(filePath);
        childrenFiles = fileList;
        updateChildrenFileList();
    }

    public FileAddress getAddress() {
        return address;
    }

    public String getParentAddress() {
        File file = new File(address.value);
        return file.getParent();
    }

    public void setAddress(FileAddress address) {
        requireNonNull(address);
        this.address = address;
        updateChildrenFileList();
    }

    private void updateChildrenFileList() {
        File folder = new File(address.value);
        List<File> files = new ArrayList<>();

        for (File file : folder.listFiles()) {
            files.add(file);
        }

        childrenFiles.setFiles(files);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof CurrentPath)) {
            return false;
        }
        CurrentPath other = (CurrentPath) obj;
        return this.address.equals(other.address) && this.childrenFiles.equals(other.childrenFiles);
    }
}
