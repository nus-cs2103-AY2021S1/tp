package chopchop.logic;

// import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import chopchop.commons.core.GuiSettings;
import chopchop.commons.core.LogsCenter;
// import chopchop.logic.commands.Command;
import chopchop.logic.commands.CommandResult;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.Model;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.ReadOnlyIngredientBook;
import chopchop.model.recipe.ReadOnlyRecipeBook;
import chopchop.model.recipe.Recipe;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.storage.Storage;

/**
 * The main CommandDispatcher governing the logic in the app.
 */
public class CommandDispatcher implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(CommandDispatcher.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code CommandDispatcher} with the given {@code Model} and {@code Storage}.
     */
    public CommandDispatcher(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    /**
     * Executes the specified command based on the commandText.
     *
     * @param commandText The command as entered by the user.
     */
    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        /*
        Command command = addressBookParser.parseCommand(commandText);
        CommandResult commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        */
        CommandResult commandResult = new CommandResult("stub");
        return commandResult;
    }

    @Override
    public ReadOnlyRecipeBook getRecipeBook() {
        return model.getRecipeBook();
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return model.getFilteredRecipeList();
    }

    @Override
    public Path getRecipeBookFilePath() {
        return model.getRecipeBookFilePath();
    }

    @Override
    public ReadOnlyIngredientBook getIngredientBook() {
        return model.getIngredientBook();
    }

    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return model.getFilteredIngredientList();
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
