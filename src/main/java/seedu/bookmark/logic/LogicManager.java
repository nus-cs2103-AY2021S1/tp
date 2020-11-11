package seedu.bookmark.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.bookmark.commons.core.GuiSettings;
import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.logic.commands.Command;
import seedu.bookmark.logic.commands.CommandResult;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.logic.parser.CommandParser;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CommandParser commandParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        commandParser = new CommandParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = commandParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveLibrary(model.getLibrary());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyLibrary getLibrary() {
        return model.getLibrary();
    }

    @Override
    public ObservableList<Book> getFilteredBookList() {
        return model.getFilteredBookList();
    }

    @Override
    public Path getLibraryFilePath() {
        return model.getBookmarkFilePath();
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
