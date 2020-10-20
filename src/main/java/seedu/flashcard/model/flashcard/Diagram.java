package seedu.flashcard.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.commons.util.AppUtil.checkArgument;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import seedu.flashcard.logic.parser.exceptions.ParseException;

/**
 * Represents the Diagram the Flashcard contains.
 */
public class Diagram {

    public static final String MESSAGE_CONSTRAINTS = "Image can be defined using relative path or absolute path";
    /*
     * The first character of the answer must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private String diagramFilePath;

    /**
     * Constructs a {@code Diagram}.
     *
     * @param diagramFilePath A valid file path.
     */
    public Diagram(String diagramFilePath) throws ParseException {
        requireNonNull(diagramFilePath);
        checkArgument(isValidImageFileType(diagramFilePath), MESSAGE_CONSTRAINTS);
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
                || (other instanceof Answer // instanceof handles nulls
                && diagramFilePath.equals(((Diagram) other).diagramFilePath)); // state check
    }

    @Override
    public int hashCode() {
        return diagramFilePath.hashCode();
    }
}