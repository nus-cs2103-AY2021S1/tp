package com.eva.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import com.eva.commons.exceptions.DataConversionException;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.staff.Staff;


/**
 * Represents a storage for {@link com.eva.model.EvaDatabase}.
 */
public interface EvaStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPersonDatabaseFilePath();

    /**
     * Returns the file path of the data file.
     */
    Path getStaffDatabaseFilePath();

    /**
     * Returns the file path of the data file.
     */
    Path getApplicantDatabaseFilePath();

    /**
     * Returns Eva data as a {@link ReadOnlyEvaDatabase}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEvaDatabase<Person>> readPersonDatabase() throws DataConversionException, IOException;

    /**
     * @see #getPersonDatabaseFilePath()
     */
    Optional<ReadOnlyEvaDatabase<Person>> readPersonDatabase(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEvaDatabase} to the storage.
     * @param evaDatabase cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePersonDatabase(ReadOnlyEvaDatabase<Person> evaDatabase) throws IOException;

    /**
     * @see #savePersonDatabase(ReadOnlyEvaDatabase)
     */
    void savePersonDatabase(ReadOnlyEvaDatabase<Person> evaDatabase, Path filePath) throws IOException;

    /**
     * Returns Eva data as a {@link ReadOnlyEvaDatabase}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEvaDatabase<Staff>> readStaffDatabase() throws DataConversionException, IOException;

    /**
     * @see #getStaffDatabaseFilePath()
     */
    Optional<ReadOnlyEvaDatabase<Staff>> readStaffDatabase(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEvaDatabase} to the storage.
     * @param evaDatabase cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStaffDatabase(ReadOnlyEvaDatabase<Staff> evaDatabase) throws IOException;

    /**
     * @see #saveStaffDatabase(ReadOnlyEvaDatabase)
     */
    void saveStaffDatabase(ReadOnlyEvaDatabase<Staff> evaDatabase, Path filePath) throws IOException;

    /**
     * Returns Eva data as a {@link ReadOnlyEvaDatabase}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEvaDatabase<Applicant>> readApplicantDatabase() throws DataConversionException, IOException;

    /**
     * @see #getApplicantDatabaseFilePath()
     */
    Optional<ReadOnlyEvaDatabase<Applicant>> readApplicantDatabase(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEvaDatabase} to the storage.
     * @param evaDatabase cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveApplicantDatabase(ReadOnlyEvaDatabase<Applicant> evaDatabase) throws IOException;

    /**
     * @see #saveApplicantDatabase(ReadOnlyEvaDatabase)
     */
    void saveApplicantDatabase(ReadOnlyEvaDatabase<Applicant> evaDatabase, Path filePath) throws IOException;
}
