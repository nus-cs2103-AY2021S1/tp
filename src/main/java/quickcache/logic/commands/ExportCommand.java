package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Paths;
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

public class ExportCommand extends Command {

    private static final Logger logger = LogsCenter.getLogger(ExportCommand.class);

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Exports a the last opened set of flashcard to specified file. "
        + "Parameters: FILE_NAME\n"
        + "Example: " + COMMAND_WORD + " CS2103_Flashcards.json";

    public static final String MESSAGE_EXPORT_FLASHCARDS_SUCCESS =
        "Flashcards exported to: %s1$s.json";

    public static final String MESSAGE_EXPORT_FLASHCARDS_FAILURE =
        "Flashcards were unable to be exported to: %s1$s.json";

    private final String fileName;

    public ExportCommand(String fileName) {
        // quickcache.json is used to store the main data
        assert !fileName.equals("quickcache.json");
        requireNonNull(fileName);
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();
        // Initialize storage at user defined file name
        QuickCacheStorage storage = new JsonQuickCacheStorage(Paths.get("data", fileName));
        //
        QuickCache lastShownQuickCache = new QuickCache();
        lastShownQuickCache.setFlashcards(lastShownList);
        try {
            storage.saveQuickCache(lastShownQuickCache);
            return new CommandResult(String.format(MESSAGE_EXPORT_FLASHCARDS_SUCCESS, fileName));
        } catch (IOException ioe) {
            logger.info("Error saving into " + fileName);
            throw new CommandException(String.format(MESSAGE_EXPORT_FLASHCARDS_FAILURE, fileName));
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
        return Objects.equals(fileName, that.fileName);
    }

}
