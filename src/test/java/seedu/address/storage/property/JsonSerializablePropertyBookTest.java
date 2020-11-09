package seedu.address.storage.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.testutil.property.TypicalProperties;

public class JsonSerializablePropertyBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonSerializablePropertyBookTest");
    private static final Path TYPICAL_PROPERTIES_FILE = TEST_DATA_FOLDER.resolve("typicalPropertyBook.json");
    private static final Path INVALID_PROPERTY_FILE = TEST_DATA_FOLDER.resolve("invalidPropertyBook.json");
    private static final Path DUPLICATE_PROPERTY_FILE = TEST_DATA_FOLDER.resolve("duplicatePropertyBook.json");

    @Test
    public void toModelType_typicalPropertiesFile_success() throws Exception {
        JsonSerializablePropertyBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PROPERTIES_FILE,
                JsonSerializablePropertyBook.class).get();
        PropertyBook propertyBookFromFile = dataFromFile.toModelType();
        PropertyBook typicalPropertyBook = TypicalProperties.getTypicalPropertyBook();
        assertEquals(propertyBookFromFile, typicalPropertyBook);
    }

    @Test
    public void toModelType_invalidBidderFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePropertyBook dataFromFile = JsonUtil.readJsonFile(INVALID_PROPERTY_FILE,
                JsonSerializablePropertyBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProperties_throwsIllegalValueException() throws Exception {
        JsonSerializablePropertyBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROPERTY_FILE,
                JsonSerializablePropertyBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePropertyBook.MESSAGE_DUPLICATE_PROPERTY,
                dataFromFile::toModelType);
    }

}
