package seedu.taskmaster.logic.commands;

import seedu.taskmaster.storage.Storage;

public abstract class StorageCommand extends Command {
    protected Storage storage;

    public void initaliseStorage(Storage storage) {
        this.storage = storage;
    }
}
