package seedu.address.storage;

import java.nio.file.Path;

public class JsonModuleStorage implements ModuleStorage {
    private Path filePath;

    public JsonModuleStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getModuleFilePath() {
        return filePath;
    }
}
