package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.GradeBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.module.Module;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final GradeBookParser gradeBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        gradeBookParser = new GradeBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = gradeBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveGradeBook(model.getGradeBook(), model.getGoalTarget());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyGradeBook getGradeBook() {
        return model.getGradeBook();
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return model.getFilteredModuleList();
    }

    @Override
    public Path getGradeBookFilePath() {
        return model.getGradeBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    /**
     * Generates the cap calculated from the list of modules.
     *
     * @return a string representation of the cap to 2 significant figures.
     */
    @Override
    public String generateCap() {
        return model.generateCapAsString();
    }

    /**
     * Filters the module list according to semester.
     *
     * @return the filtered list of modules by semester.
     */
    @Override
    public ObservableList<Module> filterModuleListBySem() {
        return model.filterModuleListBySem();
    }

    @Override
    public ObservableList<Module> sortModuleListBySem() {
        return model.sortModuleListBySem();
    }

    @Override
    public void resetFilteredList() {
        model.resetFilteredList();
    }

    @Override
    public String generateSem() {
        return model.generateSem();
    };
}
