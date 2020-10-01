package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRecipeList;

/**
 * Represents a storage for {@link seedu.address.model.RecipeList}.
 */
public interface RecipeListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRecipeListFilePath();

    /**
     * Returns RecipeList data as a {@link ReadOnlyRecipeList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyRecipeList> readRecipeList() throws DataConversionException, IOException;

    /**
     * @see #getRecipeListFilePath()
     */
    Optional<ReadOnlyRecipeList> readRecipeList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRecipeList} to the storage.
     * @param recipeList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRecipeList(ReadOnlyRecipeList recipeList) throws IOException;

    /**
     * @see #saveRecipeList(ReadOnlyRecipeList)
     */
    void saveRecipeList(ReadOnlyRecipeList recipeList, Path filePath) throws IOException;

}
