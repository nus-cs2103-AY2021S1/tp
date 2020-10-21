package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.module.Module;
import seedu.address.model.ReadOnlyTrackr;

public interface ModuleStorage {

    Path getModuleFilePath();

    Optional<ReadOnlyTrackr<Module>> readModuleList() throws DataConversionException, IOException;

    Optional<ReadOnlyTrackr<Module>> readModuleList(Path filePath) throws DataConversionException, IOException;

    void saveModuleList(ReadOnlyTrackr<Module> moduleList) throws IOException;

    void saveModuleList(ReadOnlyTrackr<Module> moduleList, Path filePath) throws IOException;
}
