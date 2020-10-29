package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;

/**
 * API of the Storage component
 */
public interface Storage extends StudentStorage, ModuleStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    //    @Override
    //    Path getStudentFilePath();
    //
    //    @Override
    //    Optional<ReadOnlyTrackr<Student>> readStudentList() throws DataConversionException, IOException;
    //
    //    @Override
    //    void saveStudentList(ReadOnlyTrackr<Student> studentList) throws IOException;

    @Override
    Path getModuleFilePath();

    @Override
    Optional<ReadOnlyTrackr<Module>> readModuleList() throws DataConversionException, IOException;

    @Override
    void saveModuleList(ReadOnlyTrackr<Module> moduleList) throws IOException;

}
