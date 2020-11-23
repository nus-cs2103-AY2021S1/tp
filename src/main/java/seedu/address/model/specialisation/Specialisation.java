package seedu.address.model.specialisation;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Specialisation of a hairdresser in HairStyleX.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSpecialisation(String)}
 */
public class Specialisation {

    enum SpecEnum {
        Color, Perm, HairExtension, Styling, HairConditioning, Straightening, ScalpTreatment, HairLossTreatment;
    }

    public static final String MESSAGE_CONSTRAINTS = "Specialisations should be one of the following options: "
            + "Color, Perm, HairExtension, Styling, "
            + "HairConditioning, Straightening, ScalpTreatment, HairLossTreatment.";
    public static final String VALIDATION_REGEX =
            "Color|Perm|HairExtension|Styling|HairConditioning|Straightening|ScalpTreatment|HairLossTreatment";

    public final SpecEnum specialisation;

    /**
     * Constructs a {@code Specialisation}.
     *
     * @param specialisation A valid specialisation.
     */
    public Specialisation(String specialisation) {
        requireNonNull(specialisation);
        checkArgument(isValidSpecialisation(specialisation), MESSAGE_CONSTRAINTS);
        switch (specialisation) {
        case "Color":
            this.specialisation = SpecEnum.Color;
            break;
        case "Perm":
            this.specialisation = SpecEnum.Perm;
            break;
        case "HairExtension":
            this.specialisation = SpecEnum.HairExtension;
            break;
        case "Styling":
            this.specialisation = SpecEnum.Styling;
            break;
        case "HairConditioning":
            this.specialisation = SpecEnum.HairConditioning;
            break;
        case "Straightening":
            this.specialisation = SpecEnum.Straightening;
            break;
        case "ScalpTreatment":
            this.specialisation = SpecEnum.ScalpTreatment;
            break;
        case "HairLossTreatment":
            this.specialisation = SpecEnum.HairLossTreatment;
            break;
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns true if a given string is a valid specialisation.
     */
    public static boolean isValidSpecialisation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Specialisation // instanceof handles nulls
                && specialisation.equals(((Specialisation) other).specialisation)); // state check
    }

    @Override
    public int hashCode() {
        return specialisation.hashCode();
    }

    /**
     * Returns specialisation in string format.
     */
    public String getSpecInString() {
        switch (specialisation) {
        case Color:
            return "Color";
        case Perm:
            return "Perm";
        case HairExtension:
            return "HairExtension";
        case Styling:
            return "Styling";
        case HairConditioning:
            return "HairConditioning";
        case Straightening:
            return "Straightening";
        case ScalpTreatment:
            return "ScalpTreatment";
        case HairLossTreatment:
            return "HairLossTreatment";
        default:
            throw new RuntimeException();
        }
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + getSpecInString() + ']';
    }

}
