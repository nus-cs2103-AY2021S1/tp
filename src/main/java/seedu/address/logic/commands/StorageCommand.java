package seedu.address.logic.commands;

import seedu.address.storage.Storage;

public abstract class StorageCommand extends Command {
    protected Storage storage;

    public void initaliseStorage(Storage storage) {
        this.storage = storage;
    }
}
