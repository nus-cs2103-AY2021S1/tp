package seedu.medibook.storage;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.commons.util.FileUtil;
import seedu.medibook.commons.util.JsonUtil;
import seedu.medibook.model.medicalnote.ReadOnlyMedicalNoteList;
import seedu.medibook.model.patient.Ic;

/**
 * A class to access MedicalNoteList data stored as a json file on the hard disk.
 */
public class JsonMedicalNoteListStorage implements MedicalNoteListStorage {

    public static final String NAME_DIR = "medicalnotes";
    public static final String NAME_EXTENSION = ".json";
    private static final Logger logger = LogsCenter.getLogger(JsonMedicalNoteListStorage.class);

    private final Path filePath;

    public JsonMedicalNoteListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getMedicalNotesDirPath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Ic ic) throws DataConversionException {
        return readMedicalNoteList(filePath, ic);
    }

    /**
     * Similar to {@link #readMedicalNoteList(Ic ic)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Path filePath, Ic ic) throws DataConversionException {
        requireNonNull(filePath);
        requireNonNull(ic);

        Path medicalNotesPath = getMedicalNotesPath(filePath, ic);
        if (!FileUtil.isFileExists(medicalNotesPath)) {
            return Optional.empty();
        }

        Optional<JsonSerializableMedicalNoteList> jsonMedicalNoteList = JsonUtil.readJsonFile(
                medicalNotesPath, JsonSerializableMedicalNoteList.class);

        try {
            return Optional.of(jsonMedicalNoteList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Ic ic) throws IOException {
        saveMedicalNoteList(medicalNoteList, filePath, ic);
    }

    /**
     * Similar to {@link #saveMedicalNoteList(ReadOnlyMedicalNoteList, Ic ic)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Path filePath, Ic ic) throws IOException {
        requireAllNonNull(medicalNoteList, filePath, ic);

        Path medicalNotesPath = getMedicalNotesPath(filePath, ic);
        FileUtil.createIfMissing(medicalNotesPath);
        JsonUtil.saveJsonFile(new JsonSerializableMedicalNoteList(medicalNoteList), medicalNotesPath);
    }

    @Override
    public void deleteMedicalNoteList(Ic ic) throws IOException {
        deleteMedicalNoteList(filePath, ic);
    }

    @Override
    public void deleteMedicalNoteList(Path filePath, Ic ic) throws IOException {
        requireAllNonNull(filePath, ic);

        Path medicalNotesPath = getMedicalNotesPath(filePath, ic);
        FileUtil.deleteIfExists(medicalNotesPath);
    }

    @Override
    public void renameMedicalNoteList(Ic oldIc, Ic newIc) throws IOException {
        renameMedicalNoteList(filePath, oldIc, newIc);
    }

    @Override
    public void renameMedicalNoteList(Path filePath, Ic oldIc, Ic newIc) throws IOException {
        requireAllNonNull(filePath, oldIc, newIc);

        Path oldMedicalNotesPath = getMedicalNotesPath(filePath, oldIc);
        Path newMedicalNotesPath = getMedicalNotesPath(filePath, newIc);
        FileUtil.renameIfExists(oldMedicalNotesPath, newMedicalNotesPath);
    }

    @Override
    public void deleteAllMedicalNoteList() throws IOException {
        deleteAllMedicalNoteList(filePath);
    }

    @Override
    public void deleteAllMedicalNoteList(Path filePath) throws IOException {
        requireNonNull(filePath);

        Path dirPath = filePath.resolve(NAME_DIR);
        Files.walk(dirPath)
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    private Path getMedicalNotesPath(Path filePath, Ic ic) {
        return filePath.resolve(NAME_DIR).resolve(ic.toString() + NAME_EXTENSION);
    }
}
