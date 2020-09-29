package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyRecipeList;

/**
 * A class to access RecipeList data stored as a json file on the hard disk.
 */
public class JsonRecipeListStorage implements RecipeListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRecipeListStorage.class);

    private Path filePath;

    public JsonRecipeListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRecipeListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyRecipeList> readRecipeList() throws DataConversionException {
        return readRecipeList(filePath);
    }

    /**
     * Similar to {@link #readRecipeList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyRecipeList> readRecipeList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableRecipeList> jsonRecipeList = JsonUtil.readJsonFile(
                filePath, JsonSerializableRecipeList.class);
        if (jsonRecipeList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRecipeList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRecipeList(ReadOnlyRecipeList recipeList) throws IOException {
        saveRecipeList(recipeList, filePath);
    }

    /**
     * Similar to {@link #saveRecipeList(ReadOnlyRecipeList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveRecipeList(ReadOnlyRecipeList recipeList, Path filePath) throws IOException {
        requireNonNull(recipeList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRecipeList(recipeList), filePath);
    }
}
