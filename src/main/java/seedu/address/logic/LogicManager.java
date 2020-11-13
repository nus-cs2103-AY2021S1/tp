package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MainCatalogueParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMainCatalogue;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MainCatalogueParser mainCatalogueParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        mainCatalogueParser = new MainCatalogueParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = mainCatalogueParser.parseCommand(commandText, model.getStatus());
        commandResult = command.execute(model);

        try {
            storage.saveMainCatalogue(model.getProjectCatalogue());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyMainCatalogue getMainCatalogue() {
        return model.getProjectCatalogue();
    }

    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return model.getFilteredProjectList();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Optional<Project> getProjectToBeDisplayedOnDashBoard() {
        return model.getProjectToBeDisplayedOnDashboard();
    }

    @Override
    public Optional<Task> getTaskToBeDisplayedOnDashboard() {
        return model.getTaskToBeDisplayedOnDashboard();
    }

    @Override
    public Optional<Participation> getTeammateToBeDisplayedOnDashboard() {
        return model.getTeammateToBeDisplayedOnDashboard();
    }

    @Override
    public Optional<Person> getPersonToBeDisplayedOnDashboard() {
        return model.getPersonToBeDisplayedOnDashboard();
    }

    @Override
    public Path getMainCatalogueFilePath() {
        return model.getProjectCatalogueFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public boolean isProjectsView() {
        switch (model.getStatus()) {
        case PROJECT_LIST:
        case PROJECT:
        case TASK:
        case TEAMMATE:
            return true;
        default:
            return false;
        }
    }
}
