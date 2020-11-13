package seedu.address.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of CliniCal data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private CliniCalStorage cliniCalStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code CliniCalStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CliniCalStorage cliniCalStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.cliniCalStorage = cliniCalStorage;
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


    // ================ CliniCal methods ==============================

    @Override
    public Path getCliniCalFilePath() {
        return cliniCalStorage.getCliniCalFilePath();
    }

    @Override
    public Optional<ReadOnlyCliniCal> readCliniCal() throws DataConversionException, IOException {
        return readCliniCal(cliniCalStorage.getCliniCalFilePath());
    }

    @Override
    public Optional<ReadOnlyCliniCal> readCliniCal(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return cliniCalStorage.readCliniCal(filePath);
    }

    @Override
    public void saveCliniCal(ReadOnlyCliniCal cliniCal) throws IOException {
        saveCliniCal(cliniCal, cliniCalStorage.getCliniCalFilePath());
    }

    @Override
    public void saveCliniCal(ReadOnlyCliniCal cliniCal, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        cliniCalStorage.saveCliniCal(cliniCal, filePath);
    }

    // ================ Image methods ==============================

    /**
     * Adds profile picture to patient and returns destination file path.
     *
     * @param patientName Patient's name
     * @param profilePic Patient's profile picture
     * @return String destination file path
     */
    public static String addPicture(String patientName, File profilePic) {
        try {
            assert patientName != "" || profilePic != null : "Patient name cannot be blank."
                                                            + "Profile picture cannot be null.";
            String profilePicPath = profilePic.getPath();
            String profilePicExtension = FilenameUtils.getExtension(profilePicPath);
            //TODO: replace hardcoded path
            String destinationPath = "data/" + patientName.replaceAll(" ", "_") + "."
                                     + profilePicExtension;

            byte[] profilePicInBytes = FileUtils.readFileToByteArray(profilePic);
            assert profilePicInBytes != null : "Profile picture cannot be null";
            String profilePicInString = Base64.getEncoder().encodeToString(profilePicInBytes);

            File finalProfilePic = new File(destinationPath);
            byte[] decodedBytes = Base64.getDecoder().decode(profilePicInString);
            assert decodedBytes != null : "Profile picture cannot be null";
            FileUtils.writeByteArrayToFile(finalProfilePic, decodedBytes);
            return destinationPath;
        } catch (IOException error) {
            return error.getMessage();
        }
    }

    /**
     * Initialises a placeholder images in the {@code data} folder.
     */
    public void initializePlaceholderImage() {
        try {
            InputStream is = MainApp.class.getResourceAsStream("/images/stock_picture.png");
            Path targetPath = cliniCalStorage.getCliniCalFilePath().getParent().resolve("stock_picture.png");

            if (Files.exists(targetPath)) {
                System.out.println("stock_picture.png already exists. Using existing file.");
                return;
            }

            FileUtils.copyInputStreamToFile(is, targetPath.toFile());
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

}
