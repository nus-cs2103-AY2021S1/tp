package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyModuleList;

import seedu.address.commons.exceptions.DataConversionException;


public interface ModuleStorage {

    Path getModuleFilePath();


    Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException;

    Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException, IOException;

    void saveModuleList(ReadOnlyModuleList moduleList) throws IOException;

    void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException;
}
