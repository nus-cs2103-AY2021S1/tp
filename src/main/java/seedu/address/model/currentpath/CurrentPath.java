package seedu.address.model.currentpath;

import seedu.address.model.tag.FileAddress;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CurrentPath {

    private static CurrentPath currentPath;
    private FileAddress address;
    private FileList childrenFiles;

    private CurrentPath() {
        address = new FileAddress(System.getProperty("user.dir"));
        updateChildrenFileList();
    }

    public static CurrentPath getInstance() {
        if (currentPath == null) {
            currentPath = new CurrentPath();
        }

        return currentPath;
    }

    public FileAddress getAddress() {
        return address;
    }

    public void setAddress(FileAddress address) {
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
}
