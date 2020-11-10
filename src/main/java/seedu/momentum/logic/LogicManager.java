//@@author

package seedu.momentum.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.logging.Logger;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.momentum.commons.core.GuiThemeSettings;
import seedu.momentum.commons.core.GuiWindowSettings;
import seedu.momentum.commons.core.LogsCenter;
import seedu.momentum.commons.core.StatisticTimeframeSettings;
import seedu.momentum.logic.commands.Command;
import seedu.momentum.logic.commands.CommandResult;
import seedu.momentum.logic.commands.exceptions.CommandException;
import seedu.momentum.logic.parser.ProjectBookParser;
import seedu.momentum.logic.parser.exceptions.ParseException;
import seedu.momentum.logic.statistic.StatisticGenerator;
import seedu.momentum.logic.statistic.StatisticManager;
import seedu.momentum.model.Model;
import seedu.momentum.model.ReadOnlyProjectBook;
import seedu.momentum.model.ViewMode;
import seedu.momentum.model.project.Project;
import seedu.momentum.model.project.TrackedItem;
import seedu.momentum.model.tag.Tag;
import seedu.momentum.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final StatisticGenerator statistic;
    private final ProjectBookParser projectBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     *
     * @param model The model containing the data to be managed.
     * @param storage The storage object that handles saving and loading the data associated with this instance.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        statistic = new StatisticManager(model);
        projectBookParser = new ProjectBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = projectBookParser.parseCommand(commandText, model);
        commandResult = command.execute(model);

        try {
            storage.saveProjectBook(model.getProjectBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        statistic.updateStatistics();
        model.updateRunningTimers();

        return commandResult;
    }

    //@@author claracheong4
    @Override
    public BooleanProperty isReminderEmpty() {
        return model.isReminderEmpty();
    }

    @Override
    public StringProperty getReminder() {
        return model.getReminder();
    }

    //@@author
    @Override
    public ReadOnlyProjectBook getProjectBook() {
        return model.getProjectBook();
    }

    @Override
    public ObjectProperty<ObservableList<TrackedItem>> getObservableDisplayList() {
        return model.getObservableDisplayList();
    }

    @Override
    public ObservableList<TrackedItem> getRunningTimers() {
        return model.getRunningTimers();
    }

    @Override
    public Path getProjectBookFilePath() {
        return model.getProjectBookFilePath();
    }

    //@@author khoodehui
    @Override
    public GuiWindowSettings getGuiWindowSettings() {
        return model.getGuiWindowSettings();
    }

    @Override
    public void setGuiWindowSettings(GuiWindowSettings guiWindowSettings) {
        model.setGuiWindowSettings(guiWindowSettings);
    }

    @Override
    public GuiThemeSettings getGuiThemeSettings() {
        return model.getGuiThemeSettings();
    }

    //@@author
    @Override
    public Set<Tag> getVisibleTags() {
        return model.getVisibleTags();
    }

    //@@author claracheong4
    @Override
    public BooleanProperty getIsTagsVisible() {
        return model.getIsTagsVisible();
    }

    //@@author khoodehui
    @Override
    public StatisticTimeframeSettings getStatisticTimeframeSettings() {
        return model.getStatisticTimeframeSettings();
    }

    //@@author boundtotheearth
    @Override
    public StatisticGenerator getStatistic() {
        return statistic;
    }

    @Override
    public ViewMode getViewMode() {
        return model.getViewMode();
    }

    //@@author pr4aveen

    @Override
    public int getTotalNumberOfItems() {
        return model.getTotalNumberOfItems();
    }

    @Override
    public Project getCurrentProject() {
        return model.getCurrentProject();
    }

}
