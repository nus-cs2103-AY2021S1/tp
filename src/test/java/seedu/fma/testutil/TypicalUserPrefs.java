package seedu.fma.testutil;


import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.fma.commons.core.GuiSettings;
import seedu.fma.model.UserPrefs;



/**
 * A utility class containing a list of {@code UserPrefs} objects to be used in tests.
 * It also contains GuiSettings and file paths.
 */
public class TypicalUserPrefs {
    public static final GuiSettings GUI_SETTINGS_A = new GuiSettings(1, 2, 3, 4);
    public static final GuiSettings GUI_SETTINGS_B = new GuiSettings(3, 2, 3, 4);

    public static final Path VALID_FILE_PATH = Paths.get("fma/book/file/path");
    public static final Path INVALID_FILE_PATH = Paths.get("invalid file path");

    public static UserPrefs getSampleUserPrefs(char character) {
        UserPrefs userPrefs = new UserPrefs();
        switch(character) {
        case 'A':
            userPrefs.setGuiSettings(GUI_SETTINGS_A);
            userPrefs.setLogBookFilePath(VALID_FILE_PATH);
            break;
        case 'B':
            userPrefs.setGuiSettings(GUI_SETTINGS_B);
            userPrefs.setLogBookFilePath(VALID_FILE_PATH);
            break;
        default:
            break;
        }
        return userPrefs;
    }

}
