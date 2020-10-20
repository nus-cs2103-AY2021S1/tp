package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import quickcache.commons.core.LogsCenter;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.model.Model;
import quickcache.model.QuickCache;
import quickcache.model.flashcard.Flashcard;
import quickcache.storage.JsonQuickCacheStorage;
import quickcache.storage.QuickCacheStorage;

/**
 * Saves the last shown flashcard list into a specified file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Exports the last opened set of flashcards to specified file. "
        + "Parameters: FILE_NAME\n"
        + "Example: " + COMMAND_WORD + " CS2103_Flashcards.json";
    public static final String MESSAGE_EXPORT_FLASHCARDS_SUCCESS =
        "Flashcards exported to: %1$s";
    public static final String MESSAGE_EXPORT_FLASHCARDS_FAILURE =
        "Flashcards were unable to be exported to: %1$s";
    private static final Logger logger = LogsCenter.getLogger(ExportCommand.class);
    private final Path path;
    private final QuickCacheStorage storage;

    /**
     * Instantiates an export command.
     *
     * @param path Path representation of the destination to save last shown flashcard list to.
     */
    public ExportCommand(Path path) {
        requireNonNull(path);
        this.path = path;
        this.storage = new JsonQuickCacheStorage(path);
    }

    /**
     * Instantiates an export command.
     *
     * @param path Path representation of the destination to save last shown flashcard list to.
     * @param storage Storage class to use for saving operations.
     */
    public ExportCommand(Path path, QuickCacheStorage storage) {
        requireNonNull(path);
        this.path = path;
        this.storage = storage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();
        // Initialize storage at user defined file name
        QuickCache lastShownQuickCache = new QuickCache();
        lastShownQuickCache.setFlashcards(lastShownList);
        try {
            storage.saveQuickCache(lastShownQuickCache);
            return new CommandResult(String.format(MESSAGE_EXPORT_FLASHCARDS_SUCCESS, path));
        } catch (IOException ioe) {
            logger.info("Error saving into " + path);
            throw new CommandException(String.format(MESSAGE_EXPORT_FLASHCARDS_FAILURE, path));
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ExportCommand that = (ExportCommand) object;
        return Objects.equals(path, that.path);
    }

}
