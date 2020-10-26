package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage, ItemListStorage, LocationListStorage, RecipeListStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Optional<ReadOnlyItemList> readItemList() throws DataConversionException, IOException;

    @Override
    void saveItemList(ReadOnlyItemList itemList) throws IOException;

    @Override
    Optional<ReadOnlyLocationList> readLocationList() throws IOException, DataConversionException;

    @Override
    Optional<ReadOnlyRecipeList> readRecipeList() throws DataConversionException, IOException;

    @Override
    void saveRecipeList(ReadOnlyRecipeList recipeList) throws IOException;

    void saveModel(Model model) throws IOException;
}
