package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.budgetpageparser.BudgetPageParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.mainpageparser.MainPageParser;
import seedu.address.model.Model;
import seedu.address.model.Renderable;
import seedu.address.model.budget.Threshold;
import seedu.address.state.Page;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String UNKNOWN_PAGE_ERROR_MESSAGE = "Could not identify current page.";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        //Logging, safe to ignore
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        Command command;
        CommandResult commandResult;

        Page currentPage = this.model.getPage();
        switch (currentPage) {
        case MAIN:
            command = new MainPageParser().parseCommand(commandText);
            commandResult = command.execute(model);
            break;
        case BUDGET:
            command = new BudgetPageParser().parseCommand(commandText);
            commandResult = command.execute(model);
            break;
        default:
            throw new CommandException(UNKNOWN_PAGE_ERROR_MESSAGE);
        }

        try {
            storage.saveNusave(model.getNusave());

        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ObservableList<Renderable> getFilteredRenderableList() {
        return model.getFilteredRenderableList();
    }

    @Override
    public Path getNusaveFilePath() {
        return model.getNusaveFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public BooleanProperty getIsBudgetPageProp() {
        return model.getBudgetPageProp();
    }

    @Override
    public StringProperty getTotalExpenditureStringProp() {
        return model.getTotalExpenditureStringProp();
    }

    @Override
    public StringProperty getThresholdStringProp() {
        return model.getThresholdStringProp();
    }

    @Override
    public String getPageTitle() {
        return this.model.getPageTitle();
    }

    @Override
    public String getTotalExpenditureValue() {
        return this.model.getTotalExpenditureValue();
    }

    @Override
    public Optional<Threshold> getThreshold() {
        return this.model.getThreshold();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public boolean isBudgetPage() {
        return this.model.isBudgetPage();
    }
}
