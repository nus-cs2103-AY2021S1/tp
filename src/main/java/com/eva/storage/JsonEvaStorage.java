package com.eva.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import com.eva.commons.core.LogsCenter;
import com.eva.commons.exceptions.DataConversionException;
import com.eva.commons.exceptions.IllegalValueException;
import com.eva.commons.util.FileUtil;
import com.eva.commons.util.JsonUtil;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.person.Person;
import com.eva.model.person.staff.Staff;

/**
 * A class to access EvaDatabase data stored as a json file on the hard disk.
 */
public class JsonEvaStorage implements EvaStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonEvaStorage.class);

    private Path personDatabaseFilePath;
    private Path staffDatabaseFilePath;

    // for compatibility of tests

    /**
     * For compatibility of tests only.
     * @param personDatabaseFilePath
     */
    public JsonEvaStorage(Path personDatabaseFilePath) {
        this.personDatabaseFilePath = personDatabaseFilePath;
        this.staffDatabaseFilePath = Paths.get("");
    }

    /**
     * Initializes with the path for person and staff database.
     * @param personDatabaseFilePath
     * @param staffDatabaseFilePath
     */
    public JsonEvaStorage(Path personDatabaseFilePath, Path staffDatabaseFilePath) {
        this.personDatabaseFilePath = personDatabaseFilePath;
        this.staffDatabaseFilePath = staffDatabaseFilePath;
    }

    public Path getPersonDatabaseFilePath() {
        return personDatabaseFilePath;
    }

    public Path getStaffDatabaseFilePath() {
        return staffDatabaseFilePath;
    }

    @Override
    public Optional<ReadOnlyEvaDatabase<Person>> readPersonDatabase() throws DataConversionException {
        return readPersonDatabase(personDatabaseFilePath);
    }

    /**
     * Similar to {@link #readPersonDatabase()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyEvaDatabase<Person>> readPersonDatabase(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonPersonDatabase> jsonEvaDatabase = JsonUtil.readJsonFile(
                filePath, JsonPersonDatabase.class);
        if (!jsonEvaDatabase.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEvaDatabase.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    public void savePersonDatabase(ReadOnlyEvaDatabase<Person> addressBook) throws IOException {
        savePersonDatabase(addressBook, personDatabaseFilePath);
    }

    /**
     * Similar to {@link #saveStaffDatabase(ReadOnlyEvaDatabase)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePersonDatabase(ReadOnlyEvaDatabase<Person> addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonPersonDatabase(addressBook), filePath);
    }

    @Override
    public Optional<ReadOnlyEvaDatabase<Staff>> readStaffDatabase() throws DataConversionException {
        return readStaffDatabase(staffDatabaseFilePath);
    }

    /**
     * Similar to {@link #readPersonDatabase()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyEvaDatabase<Staff>> readStaffDatabase(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonStaffDatabase> jsonEvaDatabase = JsonUtil.readJsonFile(
                filePath, JsonStaffDatabase.class);
        if (!jsonEvaDatabase.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEvaDatabase.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStaffDatabase(ReadOnlyEvaDatabase<Staff> addressBook) throws IOException {
        saveStaffDatabase(addressBook, staffDatabaseFilePath);
    }

    /**
     * Similar to {@link #saveStaffDatabase(ReadOnlyEvaDatabase)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStaffDatabase(ReadOnlyEvaDatabase<Staff> addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonStaffDatabase(addressBook), filePath);
    }

}
