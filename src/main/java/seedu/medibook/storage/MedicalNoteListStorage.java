package seedu.medibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.medicalnote.ReadOnlyMedicalNoteList;
import seedu.medibook.model.patient.Ic;

/**
 * Represents a storage for {@link MedicalNoteList}.
 */
public interface MedicalNoteListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMedicalNotesDirPath();

    /**
     * Returns MedicalNoteList data as a {@link ReadOnlyMedicalNoteList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @param ic cannot be null.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Ic ic) throws DataConversionException, IOException;

    /**
     * @see #readMedicalNoteList(Ic ic)
     */
    Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Path filePath, Ic ic)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMedicalNoteList} to the storage.
     * @param ic cannot be null.
     * @param medicalNoteList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Ic ic) throws IOException;

    /**
     * @see #saveMedicalNoteList(ReadOnlyMedicalNoteList, Ic ic)
     */
    void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Path filePath, Ic ic) throws IOException;

    /**
     * Deletes the data file containing the medical notes corresponding to the {@code ic}.
     * @param ic cannot be null.
     * @throws IOException if there was any problem deleting the file.
     */
    void deleteMedicalNoteList(Ic ic) throws IOException;

    /**
     * @see #deleteMedicalNoteList(Ic ic)
     */
    void deleteMedicalNoteList(Path filePath, Ic ic) throws IOException;

    /**
     * Renames the data file containing the medical notes from the old {@code ic} to the new {@code ic}.
     * @param oldIc cannot be null.
     * @param newIc cannot be null.
     * @throws IOException if there was any problem renaming the file.
     */
    void renameMedicalNoteList(Ic oldIc, Ic newIc) throws IOException;

    /**
     * @see #renameMedicalNoteList(Ic ic, Ic ic)
     */
    void renameMedicalNoteList(Path filePath, Ic oldIc, Ic newIc) throws IOException;

    /**
     * Deletes all medical notes data file.
     */
    void deleteAllMedicalNoteList() throws IOException;

    /**
     * @see #deleteAllMedicalNoteList()
     */
    void deleteAllMedicalNoteList(Path filePath) throws IOException;

}
