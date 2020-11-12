package seedu.medmoriser.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.medmoriser.commons.core.GuiSettings;
import seedu.medmoriser.commons.core.LogsCenter;
import seedu.medmoriser.logic.commands.Command;
import seedu.medmoriser.logic.commands.CommandResult;
import seedu.medmoriser.logic.commands.exceptions.CommandException;
import seedu.medmoriser.logic.parser.MedmoriserParser;
import seedu.medmoriser.logic.parser.exceptions.ParseException;
import seedu.medmoriser.model.Model;
import seedu.medmoriser.model.ReadOnlyMedmoriser;
import seedu.medmoriser.model.qanda.QAndA;
import seedu.medmoriser.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MedmoriserParser medmoriserParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        medmoriserParser = new MedmoriserParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        CommandResult commandResult;
        Command command = medmoriserParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveMedmoriser(model.getMedmoriser());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyMedmoriser getMedmoriser() {
        return model.getMedmoriser();
    }

    @Override
    public ObservableList<QAndA> getFilteredQAndAList() {
        return model.getFilteredQAndAList();
    }

    @Override
    public Path getMedmoriserFilePath() {
        return model.getMedmoriserFilePath();
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
