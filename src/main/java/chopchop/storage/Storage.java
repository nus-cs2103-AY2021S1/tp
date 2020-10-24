package chopchop.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import chopchop.commons.exceptions.DataConversionException;
import chopchop.model.usage.IngredientUsage;
import chopchop.model.usage.RecipeUsage;

/**
 * API of the Storage component
 */
public interface Storage extends IngredientBookStorage, RecipeBookStorage, UserPrefsStorage {

    public Path getRecipeUsageFilePath();

    public Optional<List<RecipeUsage>> readRecipeUsages() throws DataConversionException;

    public Optional<List<RecipeUsage>> readRecipeUsages(Path filePath) throws DataConversionException;

    public void saveRecipeUsages(List<JsonAdaptedRecipeUsage> usages) throws IOException;

    public void saveRecipeUsages(List<JsonAdaptedRecipeUsage> usages, Path filePath) throws IOException;

    public Path getIngredientUsageFilePath();

    public Optional<List<IngredientUsage>> readIngredientUsages() throws DataConversionException;

    public Optional<List<IngredientUsage>> readIngredientUsages(Path filePath) throws DataConversionException;

    public void saveIngredientUsages(List<JsonAdaptedIngredientUsage> usages) throws IOException;

    public void saveIngredientUsages(List<JsonAdaptedIngredientUsage> usages, Path filePath) throws IOException;
}
