package seedu.address.logic.commands;

import seedu.address.model.explorer.CurrentPath;
import seedu.address.model.explorer.FileList;

public class ModelStubWithCurrentPath extends ModelStub {

    private CurrentPath currentPath;

    ModelStubWithCurrentPath(String currentPath) {
        this.currentPath = new CurrentPath(currentPath, new FileList());
    }

    @Override
    public CurrentPath getCurrentPath() {
        return currentPath;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof ModelStubWithCurrentPath)) {
            return false;
        }
        ModelStubWithCurrentPath other = (ModelStubWithCurrentPath) obj;
        return this.currentPath.equals(other.currentPath);
    }
}
