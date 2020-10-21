package seedu.address.logic.commands;

import seedu.address.model.currentpath.CurrentPath;
import seedu.address.model.currentpath.FileList;

public class ModelStubWithCurrentPath extends ModelStub {

    CurrentPath currentPath = new CurrentPath(new FileList());

    @Override
    public CurrentPath getCurrentPath() {
        return currentPath;
    }
}
