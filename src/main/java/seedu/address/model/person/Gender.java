package seedu.address.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Resident's gender in Hall-y.
 * Guarantees: immutable; is valid as declared in
 */
public class Gender {
    public static final String MESSAGE_CONSTRAINTS = "Gender should either be M for male or F for female.";
    public final GenderType type;

    /**
     * Represents the type of gender, male and female
     */
    public enum GenderType {
        MALE("Male", "M"),
        FEMALE("Female", "F");

        private final String name;
        private final String option;

        GenderType(String name, String option) {
            this.name = name;
            this.option = option;
        }

        public String getOption() {
            return this.option;
        }

        public String toString() {
            return this.name;
        }
    }


    /**
     * Constructs an {@code Gender}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        type = gender.equals(GenderType.MALE.getOption()) ? GenderType.MALE : GenderType.FEMALE;
    }

    /**
     * Returns if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        return test.equals(GenderType.MALE.getOption()) || test.equals(GenderType.FEMALE.getOption());
    }


    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && type == ((Gender) other).type); // state check
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

}
