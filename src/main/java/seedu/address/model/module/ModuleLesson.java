package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a lesson in a module.
 * Guarantees: immutable; is valid as declared in {@link #isValidLesson(String)}.
 */
public class ModuleLesson {

    public static final String MESSAGE_CONSTRAINTS = "Module lesson name should only contain alphanumeric "
            + "characters, whitespaces or the hyphen character, and it should not be blank.";

    /*
     * The first character of the module lesson must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}-][\\p{Alnum}- ]*";

    /** String describing the name of a module lesson. */
    private final String lessonName;

    /**
     * Creates and initialises a new {@code ModuleLesson} object.
     *
     * @param lessonName String containing the name of the module lesson.
     */
    public ModuleLesson(String lessonName) {
        requireNonNull(lessonName);
        checkArgument(isValidLesson(lessonName), MESSAGE_CONSTRAINTS);
        this.lessonName = lessonName;
    }

    /**
     * Determines if a given String is a valid module lesson.
     *
     * @param test A given String to test.
     * @return True if the given string is a valid module lesson.
     */
    public static boolean isValidLesson(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return this.lessonName.hashCode();
    }

    @Override
    public String toString() {
        return this.lessonName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleLesson // instanceof handles nulls
                && lessonName.equals(((ModuleLesson) other).lessonName)); // state check
    }

}
