package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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
 * Saves a snapshot of the current zookeep book with a specified file name.
 */
public class SnapCommand extends Command {
    public static final String COMMAND_WORD = "snap";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Saves a snapshot of the current ZooKeep Book with the specified file name\n"
            + "Parameters: " + COMMAND_WORD + " FILE_NAME\n"
            + "Example: " + COMMAND_WORD + " zookeepbook_19-10-2020";

    public static final String MESSAGE_CONSTRAINTS =
            "File name must contain only alphanumeric characters, \"_\" and \"-\".\n"
            + "File name must not be empty and must be at most 100 characters long.";

    public static final String MESSAGE_SUCCESS = "Current ZooKeep Book saved as %s";

    public static final String MESSAGE_ERROR = "Could not save data to file: ";

    public static final String MESSAGE_WARNING = "File name %s already exists.";

    private static final String FILE_FORMAT = ".json";
    private final String fileName;

    /**
     * Creates a SnapCommand to save the current state of the ZooKeep Book in a file
     * with a specified file name.
     */
    public SnapCommand(String fileName) {
        requireNonNull(fileName);
        this.fileName = fileName + FILE_FORMAT;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Path zooKeepBookFilePath = model.getUserPrefs().getZooKeepBookFilePath();
        ZooKeepBookStorage zooKeepBookStorage = new JsonZooKeepBookStorage(zooKeepBookFilePath);
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(zooKeepBookFilePath);

        try {
            Storage storage = new StorageManager(zooKeepBookStorage, userPrefsStorage);
            ReadOnlyZooKeepBook zooKeepBook = model.getZooKeepBook();
            Path savePath = Path.of("data", fileName);

            storage.saveZooKeepBook(zooKeepBook, savePath);
        } catch (IOException ioe) {
            throw new CommandException(String.format(MESSAGE_ERROR + ioe, ioe));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
    }

    // Overloaded method that accepts a save Path destination for easier testing purposes
    protected CommandResult execute(Model model, Path path) throws CommandException {
        requireNonNull(model);

        Path zooKeepBookFilePath = model.getUserPrefs().getZooKeepBookFilePath();
        ZooKeepBookStorage zooKeepBookStorage = new JsonZooKeepBookStorage(zooKeepBookFilePath);
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(zooKeepBookFilePath);

        try {
            Storage storage = new StorageManager(zooKeepBookStorage, userPrefsStorage);
            ReadOnlyZooKeepBook zooKeepBook = model.getZooKeepBook();

            storage.saveZooKeepBook(zooKeepBook, path);
        } catch (IOException ioe) {
            throw new CommandException(String.format(MESSAGE_ERROR + ioe, ioe));
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
