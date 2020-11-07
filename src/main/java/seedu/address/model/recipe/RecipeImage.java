package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Recipe's image in the Wishful Shrinking.
 */
public class RecipeImage {
    public static final String MESSAGE_CONSTRAINTS =
            "1. Image path should not be empty \n"
            + "2. Image path should be image link address or local path, can be optional field";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}\\p{Punct}][\\p{Alnum}\\p{Punct} ]*";

    public final String imagePath;

    /**
     * Constructs a {@code Image}.
     *
     * @param imagePath A valid image path.
     */
    public RecipeImage(@JsonProperty("imagePath")String imagePath) {
        requireNonNull(imagePath);
        checkArgument(isValidImage(imagePath), MESSAGE_CONSTRAINTS);
        this.imagePath = imagePath;
    }

    /**
     * Returns string of image path.
     *
     * @return String of image path.
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Returns true if a given string is a valid instruction.
     */
    public static boolean isValidImage(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return imagePath.trim();
    }

    @Override
    public String toString() {
        return imagePath;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecipeImage // instanceof handles nulls
                && imagePath.equals(((RecipeImage) other).imagePath)); // state check
    }

    @Override
    public int hashCode() {
        return imagePath.hashCode();
    }
}
