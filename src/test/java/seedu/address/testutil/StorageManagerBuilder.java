package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.storage.JsonPresetManagerStorage;
import seedu.address.storage.JsonProfileManagerStorage;
import seedu.address.storage.JsonSerializablePresetManager;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.JsonVendorManagerStorage;
import seedu.address.storage.StorageManager;

public class StorageManagerBuilder {

    private static Path invalidPath = Paths.get("Invalid path");

    /**
     * Helps build a StorageManager class with only one functioning JsonPresetManagerStorage and takes in a boolean
     * to decide whether the testcase json file needs to be wiped or not.
     */
    public static StorageManager buildForPreset(Path presetPath, boolean reset) throws IOException {
        if (reset) {
            // Make sures to set an empty preset each time
            FileUtil.createIfMissing(presetPath);
            JsonUtil.saveJsonFile(new JsonSerializablePresetManager(new ArrayList<>(), true), presetPath);
        }
        return new StorageManager(new JsonVendorManagerStorage(invalidPath), new JsonUserPrefsStorage(invalidPath),
                new JsonPresetManagerStorage(presetPath), new JsonProfileManagerStorage(invalidPath));
    }

}
