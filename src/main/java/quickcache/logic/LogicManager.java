package quickcache.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import quickcache.commons.core.GuiSettings;
import quickcache.commons.core.LogsCenter;
import quickcache.logic.commands.Command;
import quickcache.logic.commands.CommandResult;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.logic.parser.QuickCacheParser;
import quickcache.logic.parser.exceptions.ParseException;
import quickcache.model.Model;
import quickcache.model.ReadOnlyQuickCache;
import quickcache.model.flashcard.Flashcard;
import quickcache.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final QuickCacheParser quickCacheParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        quickCacheParser = new QuickCacheParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = quickCacheParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveQuickCache(model.getQuickCache());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyQuickCache getQuickCache() {
        return model.getQuickCache();
    }

    @Override
    public ObservableList<Flashcard> getFilteredFlashcardList() {
        return model.getFilteredFlashcardList();
    }

    @Override
    public Path getQuickCacheFilePath() {
        return model.getQuickCacheFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
