package seedu.pivot.commons.util;

import static seedu.pivot.commons.core.DeveloperMessages.ASSERT_FILE_EXIST;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.logic.commands.exceptions.CommandException;

/**
 * Writes and reads files
 */
public class FileUtil {

    private static final String CHARSET = "UTF-8";

    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     */
    public static void createFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }

        createParentDirsOfFile(file);

        Files.createFile(file);
    }

    /**
     * Creates parent directories of file if it has a parent directory
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Creates directories of a specified path
     */
    public static void createDirectories(Path folderPath) throws IOException {
        if (folderPath != null) {
            Files.createDirectories(folderPath);
        }
    }

    /**
     * Assumes file exists
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes given string to a file.
     * Will create the file if it does not exist yet.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        Files.write(file, content.getBytes(CHARSET));
    }

    /**
     * Opens a document at the specified path. Path should already be valid.
     *
     */
    public static void openFile(Path file) throws IOException, CommandException {

        //check if Desktop is supported by Platform or not
        if (!Desktop.isDesktopSupported()) {
            throw new CommandException(UserMessages.MESSAGE_DESKTOP_API_NOT_AVAILABLE);
        }

        assert (isFileExists(file)) : ASSERT_FILE_EXIST;
        File doc = file.toFile();
        Desktop desktop = Desktop.getDesktop();
        desktop.open(doc);
    }

}
