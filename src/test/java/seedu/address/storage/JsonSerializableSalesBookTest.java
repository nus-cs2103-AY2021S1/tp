package seedu.address.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSerializableSalesBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableSalesBookTest");
    private static final Path TYPICAL_SALES_RECORD_ENTRY_FILE = TEST_DATA_FOLDER
            .resolve("typicalSalesEntrySalesBook.json");
    private static final Path DUPLICATE_SALES_RECORD_ENTRY_FILE = TEST_DATA_FOLDER
            .resolve("duplicateSalesEntrySalesBook.json");

}
