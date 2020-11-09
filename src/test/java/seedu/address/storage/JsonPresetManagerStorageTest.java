package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.order.OrderItem;
import seedu.address.model.preset.Preset;
import seedu.address.testutil.TypicalOrderItems;

public class JsonPresetManagerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializablePresetManagerTest");
    private static final Path STORAGE_FILE = TEST_DATA_FOLDER.resolve("storagePreset.json");
    private static final Path INVALID_PRESET_FILE = TEST_DATA_FOLDER.resolve("invalidPreset.json");
    private static final Path VALID_SAVE_FILE = TEST_DATA_FOLDER
            .resolve("savePreset.json");

    public static List<List<Preset>> getPresetList() {
        List<List<Preset>> presetList = new ArrayList<>();
        List<Preset> vendorPresets1 = new ArrayList<>();
        List<OrderItem> orderItemList = TypicalOrderItems.getTypicalOrderItems();
        Preset preset1 = new Preset("Preset 1", orderItemList);
        vendorPresets1.add(preset1);
        presetList.add(vendorPresets1);
        List<Preset> vendorPresets2 = new ArrayList<>();
        orderItemList = new ArrayList<>();
        orderItemList.add(TypicalOrderItems.PRATA);
        orderItemList.add(TypicalOrderItems.MILO);
        orderItemList.add(TypicalOrderItems.NASI_GORENG);
        Preset preset2 = new Preset("Preset 2", orderItemList);
        vendorPresets2.add(preset2);
        presetList.add(vendorPresets2);
        return presetList;
    }

    @Test
    public void readValidPresetManager_success() throws DataConversionException {
        JsonPresetManagerStorage presetManagerStorage = new JsonPresetManagerStorage(STORAGE_FILE);
        assertEquals(presetManagerStorage.readPresetManager().get(), getPresetList());
    }

    @Test
    public void readInvalidPresetFile_throwsDataConversionException() {
        JsonPresetManagerStorage presetManagerStorage = new JsonPresetManagerStorage(INVALID_PRESET_FILE);
        assertThrows(DataConversionException.class, presetManagerStorage::readPresetManager);
    }

    @Test
    public void saveValidPresetManager_success() throws IOException, DataConversionException {
        JsonPresetManagerStorage presetManagerStorage = new JsonPresetManagerStorage(VALID_SAVE_FILE);
        presetManagerStorage.savePresetManager(getPresetList());
        assertEquals(getPresetList(), presetManagerStorage.readPresetManager().get());
    }
}
