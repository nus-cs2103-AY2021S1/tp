package chopchop.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import chopchop.commons.core.GuiSettings;
import chopchop.commons.core.Log;
import chopchop.logic.autocomplete.AutoCompleter;
import chopchop.logic.commands.CommandResult;
import chopchop.logic.commands.Undoable;
import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.CommandParser;
import chopchop.logic.recommendation.RecommendationManager;
import chopchop.model.Model;
import chopchop.model.ReadOnlyEntryBook;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;
import chopchop.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager governing the logic in the app.
 */
public class LogicManager implements Logic {
    private final Log logger = new Log(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final HistoryManager historyManager;
    private final RecommendationManager recommendationManager;
    private final CommandParser parser;
    private final AutoCompleter completer;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.historyManager = new HistoryManager();
        this.recommendationManager = new RecommendationManager(model);
        this.parser = new CommandParser();
        this.completer = new AutoCompleter();
    }

    /**
     * Executes the specified command based on the commandText.
     *
     * @param commandText The command as entered by the user.
     */
    @Override
    public CommandResult execute(String commandText) {
        logger.log("command: '%s'", commandText);

        this.historyManager.addInput(commandText);
        var res = this.parser.parse(commandText);
        if (res.isError()) {
            return CommandResult.error(res.getError());
        }

        var cmd = res.getValue();
        var result = cmd.execute(this.model, this.historyManager);

        if (cmd instanceof Undoable && result.didSucceed()) {
            this.historyManager.addCommand((Undoable) cmd);
        }

        try {
            this.storage.saveRecipeUsages(this.model.getRecipeUsageList());
            this.storage.saveIngredientUsages(this.model.getIngredientUsageList());
            this.storage.saveIngredientBook(this.model.getIngredientBook());
            this.storage.saveRecipeBook(this.model.getRecipeBook());
        } catch (IOException e) {
            return CommandResult.error("Could not save data to file: %s (exception: %s)%s",
                e.getMessage(), e.getClass().getSimpleName(),
                Optional.ofNullable(e.getCause())
                    .map(c -> String.format(" (caused by: %s)", c.toString()))
                    .orElse(""));
        }

        return result;
    }

    @Override
    public String getCompletionForInput(String commandText) {
        return this.completer.getCompletionForInput(this.parser, this.model, commandText);
    }

    @Override
    public void resetCompletionState() {
        this.completer.resetCompletionState();
    }

    @Override
    public ReadOnlyEntryBook<Recipe> getRecipeBook() {
        return this.model.getRecipeBook();
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return this.model.getFilteredRecipeList();
    }

    @Override
    public Path getRecipeBookFilePath() {
        return this.model.getRecipeBookFilePath();
    }

    @Override
    public ReadOnlyEntryBook<Ingredient> getIngredientBook() {
        return this.model.getIngredientBook();
    }

    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return this.model.getFilteredIngredientList();
    }

    @Override
    public Path getIngredientBookFilePath() {
        return this.model.getIngredientBookFilePath();
    }

    @Override
    public List<String> getInputHistory() {
        return this.historyManager.getInputHistory();
    }

    @Override
    public List<String> getInputHistory(String prefix) {
        return this.historyManager.getInputHistory(prefix);
    }

    @Override
    public ObservableList<Recipe> getRecommendedRecipeList() {
        return this.recommendationManager.getRecommendedRecipeList();
    }

    @Override
    public ObservableList<Recipe> getExpiringRecipeList() {
        return this.recommendationManager.getExpiringRecipeList();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return this.model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        this.model.setGuiSettings(guiSettings);
    }
}
