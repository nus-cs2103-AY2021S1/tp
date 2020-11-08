package seedu.pivot.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.pivot.commons.core.GuiSettings;
import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.logic.commands.Command;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.parser.PivotParser;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.model.Model;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final PivotParser pivotParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        pivotParser = new PivotParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = pivotParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.savePivot(model.getPivot());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyPivot getPivot() {
        return model.getPivot();
    }

    @Override
    public ObservableList<Case> getFilteredCaseList() {
        return model.getFilteredCaseList();
    }

    @Override
    public Path getPivotFilePath() {
        return model.getPivotFilePath();
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
