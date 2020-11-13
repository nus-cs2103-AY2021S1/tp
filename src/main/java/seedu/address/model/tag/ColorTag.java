package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import javafx.scene.paint.Color;

/**
 * Represents a ColorTag in the CliniCal application.
 * Guarantees: immutable; name is valid as declared in {@link #isValidColorName(String)}
 * When a patient does not have a ColorTag, this class will be initialised with a placeholder transparent color
 * equivalent to Color.rgb(0,0,0,0).
 */
public class ColorTag {

    public static final String MESSAGE_CONSTRAINTS = "The color tag must be a standard HTML color name, or "
            + "a hex string beginning with #.";
    // Compared to other classes, this class uses the javafx Color class to check validity of the String
    // rather than a regex.

    // Keeping 2 separate strings, one for what the user originally entered, and one for code use.
    // Doing this because the code modifies the original entry of the user into something darker.
    public final String originalColor;
    public final String cssColor;

    /**
     * Constructs a {@code ColorTag}.
     *
     * @param colorName A valid color name.
     */
    public ColorTag(String colorName) {
        requireNonNull(colorName);
        if (colorName.equals("None")) {
            cssColor = "transparent";
            originalColor = "None";
        } else {
            checkArgument(isValidColorName(colorName), MESSAGE_CONSTRAINTS);
            Color tempColor = Color.web(colorName);
            cssColor = String.format("rgba(%d,%d,%d,%d)",
                    (int) (255 * tempColor.getRed()),
                    (int) (255 * tempColor.getGreen()),
                    (int) (255 * tempColor.getBlue()),
                    (int) tempColor.getOpacity());
            originalColor = colorName;
        }
    }

    /**
     * Constructs a placeholder {@code ColorTag}.
     */
    public ColorTag() {
        cssColor = "transparent";
        originalColor = "None";
    }

    /**
     * Returns true if the ColorTag is a placeholder.
     */
    public boolean isPlaceholder() {
        return cssColor.equals("transparent");
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidColorName(String test) {
        if (test.equals("None")) {
            return true;
        }

        try {
            Color.web(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ColorTag // instanceof handles nulls
                && cssColor.equals(((ColorTag) other).cssColor)); // state check
    }

    @Override
    public int hashCode() {
        return cssColor.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return originalColor;
    }

}
