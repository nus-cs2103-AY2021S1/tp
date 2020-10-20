package seedu.flashcard.model.flashcard;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * Represents the Diagram the Flashcard contains.
 */
public class Diagram {

    public static final String MESSAGE_CONSTRAINTS = "Diagram can be defined by a valid relative or absolute path";
    public static final String MESSAGE_INVALID_DIAGRAM_FILE_TYPE = "Invalid diagram file type";
    public static final String MESSAGE_NON_EXISTENT_DIAGRAM_FILE_TYPE = "Please ensure diagram file exists";

    private String diagramFilePath;

    /**
     * Constructs a {@code Diagram}.
     *
     * @param diagramFilePath A valid file path.
     */
    public Diagram(String diagramFilePath) {
        requireNonNull(diagramFilePath);
        this.diagramFilePath = diagramFilePath;
    }

    /**
     * Returns true if a given string is a valid image file type.
     */
    public static boolean isValidImageFileType(String path) {
        File file = new File(path);
        try {
            if (ImageIO.read(file) != null) {
                return true;
            }
        } catch (IOException exception) {
            return false;
        }
        return false;
    }

    /**
     * Returns true if a given string is a valid file.
     */
    public static boolean isValidFile(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return diagramFilePath;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Diagram // instanceof handles nulls
                && diagramFilePath.equals(((Diagram) other).diagramFilePath)); // state check
    }

    @Override
    public int hashCode() {
        return diagramFilePath.hashCode();
    }
}
