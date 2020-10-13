package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.RecipeList;
import seedu.address.testutil.TypicalRecipes;

public class JsonSerializableRecipeListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableRecipeListTest");
    private static final Path TYPICAL_RECIPES_FILE = TEST_DATA_FOLDER.resolve("typicalRecipesRecipeList.json");
    private static final Path INVALID_RECIPE_FILE = TEST_DATA_FOLDER.resolve("invalidRecipeRecipeList.json");
    private static final Path DUPLICATE_RECIPE_FILE = TEST_DATA_FOLDER.resolve("duplicateRecipeRecipeList.json");

    @Test
    public void toModelType_typicalRecipesFile_success() throws Exception {
        JsonSerializableRecipeList dataFromFile = JsonUtil.readJsonFile(TYPICAL_RECIPES_FILE,
                JsonSerializableRecipeList.class).get();
        RecipeList recipeListFromFile = dataFromFile.toModelType();
        RecipeList typicalRecipesRecipeList = TypicalRecipes.getTypicalRecipeList();

        assertEquals(recipeListFromFile, typicalRecipesRecipeList);
    }

    @Test
    public void toModelType_invalidRecipeFile_throwsIllegalValueException() throws Exception {
        JsonSerializableRecipeList dataFromFile = JsonUtil.readJsonFile(INVALID_RECIPE_FILE,
                JsonSerializableRecipeList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateRecipes_throwsIllegalValueException() throws Exception {
        JsonSerializableRecipeList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_RECIPE_FILE,
                JsonSerializableRecipeList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableRecipeList.MESSAGE_DUPLICATE_RECIPE,
                dataFromFile::toModelType);
    }
}
