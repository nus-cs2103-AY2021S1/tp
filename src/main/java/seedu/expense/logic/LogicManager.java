package seedu.expense.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.expense.commons.core.GuiSettings;
import seedu.expense.commons.core.LogsCenter;
import seedu.expense.logic.commands.Command;
import seedu.expense.logic.commands.CommandResult;
import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.logic.parser.ExpenseBookParser;
import seedu.expense.logic.parser.exceptions.ParseException;
import seedu.expense.model.Model;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.expense.Expense;
import seedu.expense.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ExpenseBookParser expenseBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        expenseBookParser = new ExpenseBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = expenseBookParser.parseCommand(commandText, model.getAliasMap());
        commandResult = command.execute(model);

        try {
            storage.saveExpenseBook(model.getExpenseBook());
            storage.saveAliasMap(model.getAliasMap());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyExpenseBook getExpenseBook() {
        return model.getExpenseBook();
    }

    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return model.getFilteredExpenseList();
    }

    @Override
    public Path getExpenseBookFilePath() {
        return model.getExpenseBookFilePath();
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
