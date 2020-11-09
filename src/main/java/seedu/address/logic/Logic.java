package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.InventoryComponent;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.item.Item;
import seedu.address.model.location.Location;
import seedu.address.model.recipe.Recipe;
import seedu.address.ui.DisplayedInventoryType;

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
    CommandResult execute(String commandText) throws CommandException, ParseException, IOException;

    /** Returns the ItemList */
    ReadOnlyItemList getItemList();

    /** Returns an unmodifiable view of the filtered list of items */
    ObservableList<Item> getFilteredItemList();

    /** Returns the user prefs' item list file path. */
    Path getItemListFilePath();

    /** Returns the LocationList */
    ReadOnlyLocationList getLocationList();

    /** Returns an unmodifiable view of the filtered list of locations */
    ObservableList<Location> getFilteredLocationList();

    /** Returns the user prefs' location list file path. */
    Path getLocationListFilePath();

    /** Returns the RecipeList */
    ReadOnlyRecipeList getRecipeList();

    /** Returns an unmodifiable view of the filtered list of recipes */
    ObservableList<Recipe> getFilteredRecipeList();

    /** Returns the user prefs' recipe list file path. */
    Path getRecipeListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the relevant inventory list to be displayed.
     */
    ArrayList<InventoryComponent> getInventoryList(DisplayedInventoryType inventoryType);
}
