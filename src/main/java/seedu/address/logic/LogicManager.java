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
import seedu.address.logic.parser.CommonCentsParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyCommonCents;
import seedu.address.model.account.Account;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.ActiveAccountManager;
import seedu.address.model.account.Name;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private static final int FIRST_ACCOUNT_INDEX = 0;
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CommonCentsParser commonCentsParser;
    private final ActiveAccount activeAccount;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.activeAccount = new ActiveAccountManager(model.getFilteredAccountList().get(FIRST_ACCOUNT_INDEX));

        commonCentsParser = new CommonCentsParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        // Logging, safe to ignore
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        // Parses user input from String to Command
        Command command = commonCentsParser.parseCommand(commandText);
        // Executes the Command and stores the result in commandResult
        commandResult = command.execute(model, activeAccount);

        try {
            // Since the model is modified previously, the current model is saved through the storage
            storage.saveCommonCents(model.getCommonCents());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return commandResult;
    }

    @Override
    public ReadOnlyCommonCents getCommonCents() {
        return model.getCommonCents();
    }

    @Override
    public ObservableList<Account> getFilteredAccountList() {
        return model.getFilteredAccountList();
    }

    @Override
    public Name getActiveAccountName() {
        return activeAccount.getAccount().getName();
    }

    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return activeAccount.getFilteredExpenseList();
    }

    @Override
    public ObservableList<Revenue> getFilteredRevenueList() {
        return activeAccount.getFilteredRevenueList();
    }

    @Override
    public double getTotalRevenue() {
        return activeAccount.getTotalRevenue();
    }

    @Override
    public double getTotalExpense() {
        return activeAccount.getTotalExpenses();
    }

    @Override
    public Path getCommonCentsFilePath() {
        return model.getCommonCentsFilePath();
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
