package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.person.Student;

/**
 * Manages storage of Trackr data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private StudentStorage studentStorage;
    private ModuleStorage moduleStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given storages.
     */
    public StorageManager(StudentStorage studentStorage, ModuleStorage moduleStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.studentStorage = studentStorage;
        this.moduleStorage = moduleStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ Student methods ==============================

//    @Override
//    public Path getStudentFilePath() {
//        return studentStorage.getStudentFilePath();
//    }
//
//    @Override
//    public Optional<ReadOnlyTrackr<Student>> readStudentList() throws DataConversionException, IOException {
//        return readStudentList(studentStorage.getStudentFilePath());
//    }
//
//    @Override
//    public Optional<ReadOnlyTrackr<Student>> readStudentList(Path filePath)
//            throws DataConversionException, IOException {
//        logger.fine("Attempting to read student data from file: " + filePath);
//        return studentStorage.readStudentList(filePath);
//    }
//
//    @Override
//    public void saveStudentList(ReadOnlyTrackr<Student> studentList) throws IOException {
//        saveStudentList(studentList, studentStorage.getStudentFilePath());
//    }
//
//    @Override
//    public void saveStudentList(ReadOnlyTrackr<Student> studentList, Path filePath) throws IOException {
//        logger.fine("Attempting to write to student data file: " + filePath);
//        studentStorage.saveStudentList(studentList, filePath);
//    }


    // ================ Module methods ==============================

    @Override
    public Path getModuleFilePath() {
        return moduleStorage.getModuleFilePath();
    }

    @Override
    public Optional<ReadOnlyTrackr<Module>> readModuleList() throws DataConversionException, IOException {
        return readModuleList(moduleStorage.getModuleFilePath());
    }

    @Override
    public Optional<ReadOnlyTrackr<Module>> readModuleList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read module data from file: " + filePath);
        return moduleStorage.readModuleList(filePath);
    }

    @Override
    public void saveModuleList(ReadOnlyTrackr<Module> moduleList) throws IOException {
        saveModuleList(moduleList, moduleStorage.getModuleFilePath());
    }

    @Override
    public void saveModuleList(ReadOnlyTrackr<Module> moduleList, Path filePath) throws IOException {
        logger.fine("Attempting to write to module data file: " + filePath);
        moduleStorage.saveModuleList(moduleList, filePath);
    }

}
