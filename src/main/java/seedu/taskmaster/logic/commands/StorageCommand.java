package seedu.taskmaster.logic.commands;

import seedu.taskmaster.storage.Storage;

public abstract class StorageCommand extends Command {
    protected Storage storage;

    public void initialiseStorage(Storage storage) {
        this.storage = storage;
    }
}
