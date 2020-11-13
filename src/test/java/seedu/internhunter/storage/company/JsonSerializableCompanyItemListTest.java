package seedu.internhunter.storage.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.commons.util.JsonUtil;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.storage.JsonSerializableItemList;

public class JsonSerializableCompanyItemListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableCompanyItemListTest");
    private static final Path SAMPLE_COMPANY_ITEMS_FILE = TEST_DATA_FOLDER.resolve(
            "sampleCompanyItemList.json");
    private static final Path INVALID_COMPANY_ITEM_FILE = TEST_DATA_FOLDER.resolve(
            "invalidCompanyItemList.json");
    private static final Path DUPLICATE_COMPANY_ITEM_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateCompanyItemList.json");

    @Test
    public void toModelType_sampleCompanyItemsFile_success() throws Exception {
        JsonSerializableItemList<CompanyItem, JsonAdaptedCompanyItem> dataFromFile = JsonUtil.readJsonFile(
                SAMPLE_COMPANY_ITEMS_FILE, CompanyItem.class, JsonAdaptedCompanyItem.class).get();
        ItemList<CompanyItem> addressBookFromFile = dataFromFile.toModelType();
        ItemList<CompanyItem> sampleCompanyItemListStorage = getSampleCompanyList();
        assertEquals(addressBookFromFile, sampleCompanyItemListStorage);
    }

    @Test
    public void toModelType_invalidCompanyItemFile_throwsIllegalValueException() throws Exception {
        JsonSerializableItemList<CompanyItem, JsonAdaptedCompanyItem> dataFromFile = JsonUtil.readJsonFile(
                INVALID_COMPANY_ITEM_FILE, CompanyItem.class, JsonAdaptedCompanyItem.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCompanyItems_throwsIllegalValueException() throws Exception {
        JsonSerializableItemList<CompanyItem, JsonAdaptedCompanyItem> dataFromFile = JsonUtil.readJsonFile(
                DUPLICATE_COMPANY_ITEM_FILE, CompanyItem.class, JsonAdaptedCompanyItem.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableItemList.MESSAGE_DUPLICATE_ITEM,
                dataFromFile::toModelType);
    }

}
