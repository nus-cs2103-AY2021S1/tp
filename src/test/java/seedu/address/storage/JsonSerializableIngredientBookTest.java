package seedu.address.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSerializableIngredientBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableIngredientBookTest");
    private static final Path TYPICAL_INGREDIENT_FILE = TEST_DATA_FOLDER.resolve("typicalIngredientsAddressBook.json");
    private static final Path DUPLICATE_INGREDIENT_FILE = TEST_DATA_FOLDER
            .resolve("duplicateIngredientAddressBook.json");

}
