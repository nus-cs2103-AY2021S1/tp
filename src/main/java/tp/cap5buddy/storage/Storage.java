package tp.cap5buddy.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import tp.cap5buddy.modules.ModuleList;


/**
 * API of the Storage component
 */
public interface Storage extends ModuleListStorage {
    @Override
    Path getModuleListFilePath();

    @Override
    Optional<ModuleList> readModuleList() throws DataConversionException, IOException;

    @Override
    void saveModuleList(ModuleList moduleList) throws IOException;

}
