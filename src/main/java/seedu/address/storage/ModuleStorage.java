package seedu.address.storage;

<<<<<<< HEAD
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyModuleList;

public interface ModuleStorage {

    Path getModuleFilePath();


    Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException;

    Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException, IOException;

    void saveModuleList(ReadOnlyModuleList moduleList) throws IOException;

    void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException;
=======
import java.nio.file.Path;

public interface ModuleStorage {
    Path getModuleFilePath();
>>>>>>> origin/module-storage
}
