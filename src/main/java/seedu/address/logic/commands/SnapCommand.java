package seedu.address.logic.commands;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyZooKeepBook;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.JsonZooKeepBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.ZooKeepBookStorage;

/**
 * Saves a snapshot of the current zookeep book.
 */
public class SnapCommand extends Command {
    public static final String COMMAND_WORD = "snap";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Saves a snapshot of the current ZooKeep Book with the specified file name\n"
            + "Parameters: " + COMMAND_WORD + " FILE_NAME\n"
            + "Example: " + COMMAND_WORD + " zookeepbook_19-10-2020";

    public static final String MESSAGE_CONSTRAINTS =
            "File name must contain only alphanumeric characters, \"_\" and \"-\"."
            + " File name must not be empty.";

    public static final String MESSAGE_SUCCESS = "Current ZooKeep Book saved as %s";

    private final String fileName;

    public SnapCommand(String fileName) {
        this.fileName = fileName + ".json";
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Path zooKeepBookFilePath = model.getUserPrefs().getZooKeepBookFilePath();
        ZooKeepBookStorage zooKeepBookStorage = new JsonZooKeepBookStorage(zooKeepBookFilePath);
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(zooKeepBookFilePath);

        Storage storage = new StorageManager(zooKeepBookStorage, userPrefsStorage);
        ReadOnlyZooKeepBook zooKeepBook = model.getZooKeepBook();

        try {
            Path savePath = Path.of("data", fileName);
            storage.saveZooKeepBook(zooKeepBook, savePath);
        } catch (IOException ioe) {
            throw new CommandException("Error saving");
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SnapCommand // instanceof handles nulls
                && fileName.equals(((SnapCommand) other).fileName));
    }
}
