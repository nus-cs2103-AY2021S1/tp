package seedu.address.logic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyWishfulShrinking;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException, IOException, URISyntaxException;

    /**
     * Returns the WishfulShrinking.
     *
     * @see seedu.address.model.Model#getWishfulShrinking()
     */
    ReadOnlyWishfulShrinking getWishfulShrinking();

    /** Returns an unmodifiable view of the filtered list of recipes */
    ObservableList<Recipe> getFilteredRecipeList();

    /** Returns an unmodifiable view of the filtered list of ingredients */
    ObservableList<Ingredient> getFilteredIngredientList();

    /** Returns an unmodifiable view of the filtered list of consumption */
    ObservableList<Consumption> getFilteredConsumptionList();

    /**
     * Returns the user prefs' Wishful Shrinking file path.
     */
    Path getWishfulShrinkingFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
